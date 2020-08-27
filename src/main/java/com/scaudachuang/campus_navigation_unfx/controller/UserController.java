package com.scaudachuang.campus_navigation_unfx.controller;

import com.alibaba.fastjson.JSONObject;
import com.scaudachuang.campus_navigation_unfx.POJO.LoginParameters;
import com.scaudachuang.campus_navigation_unfx.POJO.LoginResult;
import com.scaudachuang.campus_navigation_unfx.config.WxAppConfig;
import com.scaudachuang.campus_navigation_unfx.controller.wxException.WxConnectionException;
import com.scaudachuang.campus_navigation_unfx.entity.User;
import com.scaudachuang.campus_navigation_unfx.service.UserService;
import com.scaudachuang.campus_navigation_unfx.utils.http.HttpClientUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回LoginResult json
 * {
 *      "status": xxx,200 500
 *      "definedLogStatus": "xxx",
 *      "timestamp": "yyyy-MM-dd HH:mm:ss",
 *      "msg": xxx
 * }
 */
@RestController
@RequestMapping("/login")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private UserService userService;

    @Resource
    private WxAppConfig wxAppConfig;

    /**
     * 入库
     * @return 自定义登陆状态String
     */
    @PostMapping(value = "/decodeUserInfo",produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResult decodeUserInfo(@RequestBody LoginParameters loginParameters)
            throws URISyntaxException {
        String code = loginParameters.getCode();
        String iv = loginParameters.getIv();
        String encryptedData = loginParameters.getEncryptedData();
        LoginResult ret;
        //用户的完整信息userInfo
        try{
            JSONObject jsonObject = this.getWxResult(code);
            String sessionKey = jsonObject.get("session_key").toString();
            String open_id = jsonObject.get("openid").toString();
            JSONObject userInfo = getUserInfo(encryptedData, sessionKey, iv);
            User user = userService.findByOpenId(open_id);
            int userId;
            if (user != null){
                //user is existed 说明用户清除授权，再次授权
                userService.updateUserByOpenId(open_id,userInfo);//更新用户信息
                logger.info("Authorization successful - " + user.getUserName());
                userId = user.getId();
            }else {
                //user is not existed 说明用户第一次授权
                String user_nameAndDefinedLoginStatus = userService.insertRegUser(userInfo,open_id,sessionKey);
                String[] strings = user_nameAndDefinedLoginStatus.split(" ");
                logger.info("Authorization successful - " + strings[0]);
                userId = Integer.parseInt(strings[1]);
            }

            ret = new LoginResult(
                    200,
                    "Authorization successful.",
                    dateFormat.format(new Date()),
                    userId);

            return ret;
        } catch (NoSuchAlgorithmException | BadPaddingException |
                InvalidKeyException | InvalidAlgorithmParameterException |
                NoSuchPaddingException | InvalidParameterSpecException |
                NoSuchProviderException | IllegalBlockSizeException |
                IOException | WxConnectionException e) {
            ret = new LoginResult(
                    500,
                    "Decryption failed.",
                    dateFormat.format(new Date()));
            logger.error("Decryption failed: " + e.getMessage());
            return ret;
        }
    }

    public JSONObject getWxResult(String code ) throws URISyntaxException, IOException, WxConnectionException {
        if(code == null||code.length() == 0)
            throw new WxConnectionException("Code error.");
        Map<String, String> param = new HashMap<>();
        param.put("appid", wxAppConfig.getWX_LOGIN_APP_ID());
        param.put("secret", wxAppConfig.getWX_LOGIN_SECRET());
        param.put("js_code",code);
        param.put("grant_type", wxAppConfig.getWX_LOGIN_GRANT_TYPE());
        String wxResult = HttpClientUtil.doGet(wxAppConfig.getWX_LOGIN_URL(),param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);

        if (jsonObject == null) throw new WxConnectionException("WxService connection error. But no result.");
        else if (jsonObject.getString("openid") == null || jsonObject.getString("session_key") == null){
            throw new WxConnectionException("WxService connection error. " +
                    "error_code: " + jsonObject.getString("errorcode") + " err_msg: " + jsonObject.getString("errmsg")
            ); }
        else return jsonObject;
    }

    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv) throws NoSuchPaddingException,
            NoSuchAlgorithmException, NoSuchProviderException, InvalidParameterSpecException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyByte.length % base != 0) {
            int groups = keyByte.length / base + 1;
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
            keyByte = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        parameters.init(new IvParameterSpec(ivByte));
        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
        byte[] resultByte = cipher.doFinal(dataByte);
        if (null != resultByte && resultByte.length > 0) {
            String result = new String(resultByte, StandardCharsets.UTF_8);
            return JSONObject.parseObject(result);
        }
        return null;
    }
}

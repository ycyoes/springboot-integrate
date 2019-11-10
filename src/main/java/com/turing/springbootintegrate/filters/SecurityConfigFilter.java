package com.turing.springbootintegrate.filters;

import java.util.Properties;


import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

//密码加密，重写filter中解密方法
public class SecurityConfigFilter extends ConfigFilter{

	@Override
	public void decrypt(DruidDataSource dataSource, Properties info) {
		System.out.println("--------自定义过滤器-----------");
        try {
            String encryptedPassword = null;
            if (info != null) {
                encryptedPassword = info.getProperty(DruidDataSourceFactory.PROP_PASSWORD);
            }

            if (encryptedPassword == null || encryptedPassword.length() == 0) {
                encryptedPassword = dataSource.getConnectProperties().getProperty(DruidDataSourceFactory.PROP_PASSWORD);
            }

            if (encryptedPassword == null || encryptedPassword.length() == 0) {
                encryptedPassword = dataSource.getPassword();
            }

            //PublicKey publicKey = getPublicKey(dataSource.getConnectProperties(), info);
            System.out.println("加密后密码： " +  encryptedPassword);
            
            //目前使用base64加密
			/*
			 * String passwordPlainText = new
			 * String(Base64.decodeBase64(encryptedPassword),"utf-8");
			 */
//			 EncryptDecryptService encryptDecryptService = ((DuridDataSource4Oscar)dataSource).getEncryptDecryptService();
			  
//			 String passwordPlainText = encryptDecryptService.decrypt(encryptedPassword);
            
            //String passwordPlainText = "dra";
            if (info != null) {
//                info.setProperty(DruidDataSourceFactory.PROP_PASSWORD, passwordPlainText);
                info.setProperty(DruidDataSourceFactory.PROP_PASSWORD, encryptedPassword);
            } else {
//                dataSource.setPassword(passwordPlainText);
                dataSource.setPassword(encryptedPassword);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to decrypt.", e);
        }
	}
	

	
}

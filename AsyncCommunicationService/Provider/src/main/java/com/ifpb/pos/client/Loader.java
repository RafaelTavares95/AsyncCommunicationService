/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.client;

import com.ifpb.pos.provider.channel.process.ProcessService;
import com.ifpb.pos.provider.channel.register.RegisterService;
import com.ifpb.pos.provider.channel.response.ResponseService;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Rafael
 */
public class Loader {    
    public static void main(String[] args) throws MalformedURLException, InterruptedException {        
        String token = register("c3po");
        sendMessage(token, "ae carai");
        System.out.println(getResponse(token));
    }
    
    private static String register(String id) throws MalformedURLException{
        URL url=new URL("http://localhost:8008/register?wsdl");
        QName qName=new QName("http://register.channel.provider.pos.ifpb.com/", 
                "RegisterServiceImplService");
        Service service=Service.create(url,qName);
        RegisterService register=service.getPort(RegisterService.class);
        
        return register.registerMyself(id);
    }
    
    private static void sendMessage(String token, String msg) throws MalformedURLException{
        URL url=new URL("http://localhost:8008/process?wsdl");
        QName qName=new QName("http://process.channel.provider.pos.ifpb.com/", 
                "ProcessServiceImplService");
        Service service=Service.create(url,qName);
        ProcessService process = service.getPort(ProcessService.class);
        process.processCall(token, msg);
    }
    
    private static String getResponse(String token) throws MalformedURLException, InterruptedException{
        URL url=new URL("http://localhost:8008/response?wsdl");
        QName qName=new QName("http://response.channel.provider.pos.ifpb.com/", 
                    "ResponseServiceImplService");
        Service service=Service.create(url,qName);
        ResponseService response = service.getPort(ResponseService.class);
        String result = "";
        while (result.equals("")) {
            Thread.sleep(500 * 60);
            result = response.getResponse(token);
        }
        return result;
    }
}

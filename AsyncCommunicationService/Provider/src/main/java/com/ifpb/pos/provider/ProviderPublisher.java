/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.pos.provider;

import com.ifpb.pos.provider.channel.process.ProcessServiceBackground;
import com.ifpb.pos.provider.channel.process.ProcessServiceImpl;
import com.ifpb.pos.provider.channel.register.RegisterServiceImpl;
import com.ifpb.pos.provider.channel.response.ResponseServiceImpl;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Rafael
 */
public class ProviderPublisher {
    public static void main(String[] args) throws InterruptedException {        
        Endpoint.publish("http://localhost:8008/register",new RegisterServiceImpl());
        
        Endpoint.publish("http://localhost:8008/process",new ProcessServiceImpl());
        
        Endpoint.publish("http://localhost:8008/response",new ResponseServiceImpl());
        
        ProcessServiceBackground proc = new ProcessServiceBackground();
        proc.process();
    }
}

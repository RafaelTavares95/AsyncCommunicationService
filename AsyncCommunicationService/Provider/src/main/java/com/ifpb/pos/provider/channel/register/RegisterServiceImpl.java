/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.provider.channel.register;

import com.ifpb.pos.provider.Repository;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.jws.WebService;

/**
 *
 * @author Rafael
 */
@WebService(endpointInterface = "com.ifpb.pos.provider.channel.register.RegisterService")
public class RegisterServiceImpl implements RegisterService{
    
    
    /**
     * registra um novo cliente e retorna a sua identificação única
     * @param id
     * @return 
     */
    @Override
    public String registerMyself(String id) {
        String hash = gerarHash(id);
        String uid = id + hash;
        Repository.getInstance().getClientesRegistrados().add(uid);
        System.out.println("[Registrando Cliente com o TokenID: " + uid.toUpperCase() + " em: " + new Date() + "]");
        return uid;
    }
    
    /**
     * gera um hash do id com o tempo
     * @param id
     * @return 
     */
    public String gerarHash(String id){
        String m = "";
        Long d = System.currentTimeMillis();
        id=id+d.toString();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(id.getBytes()));
        m = hash.toString(16);
        return m;
    }
}

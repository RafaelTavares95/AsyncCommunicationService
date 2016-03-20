/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.provider.channel.response;

import com.ifpb.pos.provider.Repository;
import javax.jws.WebService;

/**
 *
 * @author Rafael
 */
@WebService(endpointInterface = "com.ifpb.pos.provider.channel.response.ResponseService")
public class ResponseServiceImpl implements ResponseService{

    /**
     * verifica se a resposta já esta no repositorio e retorna ela caso for verdade
     * @param id
     * @return 
     */
    @Override
    public String getResponse(String id) {
        if(Repository.getInstance().getResponseRepository().containsKey(id)){
            Repository.getInstance().getClientesRegistrados().remove(id);
            System.out.println("[Entregando mensagem do cliente com TokenId: " + id.toUpperCase() + "]");
            return Repository.getInstance().getResponseRepository().get(id);
        }    
        return "";
    }
    
}

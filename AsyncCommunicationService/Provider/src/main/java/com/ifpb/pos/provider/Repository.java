/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Rafael
 */
public class Repository {
    private static Repository instance;
    private List<String> clientesRegistrados;
    private Map<String,String> responseRepository;
    private ConcurrentLinkedQueue<String> filaDeRequisicoes;
    
    private Repository(){
        this.clientesRegistrados = new ArrayList<>();
        this.responseRepository = new HashMap<>();
        this.filaDeRequisicoes = new ConcurrentLinkedQueue();
    }
    
    public static Repository getInstance(){
        if (instance == null) {
            instance = new Repository();
	}
        return instance;
    }

    public List<String> getClientesRegistrados() {
        return clientesRegistrados;
    }

    public Map<String, String> getResponseRepository() {
        return responseRepository;
    }

    public ConcurrentLinkedQueue getFilaDeRequisicoes() {
        return filaDeRequisicoes;
    }
}

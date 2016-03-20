/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.provider.channel.process;

import com.ifpb.pos.provider.Repository;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rafael
 */
public class ProcessServiceBackground {
    private static final long TEMPO = (300 * 60);
    
    public void process() throws InterruptedException{
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            public void run() {
                try {
                    if(!Repository.getInstance().getFilaDeRequisicoes().isEmpty()){
                        String poll = (String) Repository.getInstance().getFilaDeRequisicoes().poll();
                        //Só pra simular o processamento
                        String[] split = poll.split(":");
                        String id= split[0];
                        String msg= split[1];
                        System.out.println("[Retirando da fila o cliente com o TokenId: " + id.toUpperCase() + "]");
                        Thread.sleep(100 * 60);
                        Long time = System.currentTimeMillis();
                        String ts = time.toString();
                        //
                        Repository.getInstance().getResponseRepository().put(id, msg+ts);
                        System.out.println("[Colocando na lista de respostas a mensagem do "
                                + "cliente com o TokenId: " + id.toUpperCase() + "]");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, TEMPO);
            
    }
}

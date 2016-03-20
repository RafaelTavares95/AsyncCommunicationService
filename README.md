# AsyncCommunicationService
Projeto didático sobre a os problemas que envolver comunicação assíncrona.
****
**Professor: Aristofânio Garcia de Araújo**</br>
**Disciplina: Programação Orientada a Serviços**</br>
**IFPB-Campus Cajazeiras - Análise e Desenvolvimento de Sistemas**
****	
## Problemática ##

- Há operações disponibilizadas em serviços que
requerem um longo tempo de processamento;
Exemplo: solicitação de pagamento de cartões de crédito em
sistemas de e-commerce.

- Aguardar o processamento de operações longas
podem gerar sobrecarga de conexões;
- Se a conexão cair antes da operação se completar,
será necessário uma nova chamada para a operação;
- Se a operação tiver um tempo de processamento
desconhecido ou com uma variação seja muito
grande, não será possível garantir uma conexão
síncrona de forma eficiente;
- Pode ser necessário dividir operações de longo prazo
em parte menores para reduzir o tempo de duração
do processamento, criando um problema de
integridade transacional;
- Em sistemas que exigem muitas requisições, será
necessário ampliar os recursos para atender a
diversas requisições concorrentes.
## Soluções ##
1. Subdividir as operações em sub-operações mantendo
todas sobre um gerenciador de transação
 - Vantagens:
	 - Reduzir o tempo de processamento de uma operação;
	 - Possibilidade de reuso maior por parte da operação;
 - Desvantagens:
	 - Maior complexidade para transacionar uma única operação;
	 - Maior quantidade de requisições por parte do cliente;
	
1. Replicar recursos
 - Vantagens:
	 - Reduzir o tempo de processamento de uma operação;
	 - Maior disponibilidade do serviço;
 - Desvantagens:
	 - Aumento do custo de manutenção da infraestrutura;
	 - Outros recurso, não replicados, poderão se tornar um
gargalo e eliminar a vantagem desta solução. Exemplo:</br>
• Adotar o mesmo banco de dados para todos os nós/
componentes do sistema;

1. Construir comunicação assíncrona
 - Vantagens:
	 - Reduzir o tempo de processamento de uma operação;
	 - Menor custo-benefício sobre a implementação e
manutenção;
 - Desvantagens:
	 - Requer um gerenciamento de atividades mais complexo;
	 - Cria maior complexidade na implementação do lado do
cliente, requerendo que adote um estilo arquitetural
baseado em eventos ou técnicas de recuperação secundária
de mensagens, como **pulling**;

###Modelo de Solução###
(Baseado na técnica de pulling)

![Solucao](https://github.com/RafaelTavares95/AsyncCommunicationService/blob/master/imagens/Solucao.png)

## Descrição do Exercício ##
Uma determinada operação de um serviço web possui
um tempo de processamento que varia muito (motivo não
conhecido) e devido a importância desta operação,
existem muitos consumidores interessados, logo muitas
requisições são esperadas. A preocupação é que os
clientes do serviço possam consumir muita rede e que as
mensagens possam ser trocadas em caso de falhas na
comunicação. Diante desta demanda, de que forma
poderia ser arquitetado um Sistema Distribuído baseado
em Web Service para reduzir ou eliminar estas
preocupações?



- Analisando o problema:
 - Muitas requisições simultâneas sobre uma operação
executada sobre tempo desconhecido:
		- implica em muitas requisições simultâneas;
		- consumo excessivo de banda de rede;
		- utilização de recursos da máquina servidora ineficiente;
 - Preocupação a integridade das mensagens trocadas:
		- Implica em falha de processamento;
		- Pode criar erros inesperados para o sistema;
		- Impossibilidade de saber qual requisição causou a falha;

- Uma solução viável:
 - Eliminar o tempo de espera pelo processamento das
mensagens através de uma comunicação assíncrona;
 - Adotar a técnica de pulling para descobrir quando a
resposta para uma requisição estará pronta;
 - Identificar todas as mensagens baseadas em um pool de
identidades;
 - Adotar 3 canais de comunicão: o primeiro para
requisitar um identificador, o segundo para enviar uma
mensagem identificada e o terceiro para recuperar a
resposta da mensagem;

###Arquitetura###
![Arquitetura](https://github.com/RafaelTavares95/AsyncCommunicationService/blob/master/imagens/Arquitetura.png)
###Componentes###
![Componentes](https://github.com/RafaelTavares95/AsyncCommunicationService/blob/master/imagens/Componentes.png)
###Fluxo###
![Fluxo](https://github.com/RafaelTavares95/AsyncCommunicationService/blob/master/imagens/Fluxo.png)

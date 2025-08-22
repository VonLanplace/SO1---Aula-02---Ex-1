<p align="center">
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="Java Icon" height="40" width="40">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/eclipse/eclipse-original.svg" alt="Eclipse Icon" height="40" width="40">
</p>

# Sistemas Operacionais 1 - Aula 2 - Ex 1

## 🗓️ Aula 2

## 💻 Exercício 1
Criar em Eclipse, um novo Java Project com uma classe chamada RedesController.java no package controller e uma classe Main.java no package view.
A classe RedesController.java deve ter 3 métodos.
1) O primeiro, chamado os, que identifica e retorna o nome do Sistema Operacional (Fazê-lo privado)
2) O segundo, chamado ip, que verifica o Sistema Operacional e, de acordo com o S.O., faz a chamada de configuração de IP.
- A leitura do processo chamado deve verificar cada linha e, imprimir, apenas, o nome do adaptador de rede e o IPv4, portanto, adaptadores sem IPv4 não devem ser mostrados
3) O terceiro, chamado ping, que verifica o Sistema Operacional e, de acordo com o S.O. e, faz a chamada de ping em IPv4 com 10 iterações.
- A leitura do processo chamado deve verificar as linhas de saída e exibir, apenas, o tempo médio do ping.
- O teste de ping deve ser feito com a URL www.google.com.br
- A Classe Main.java deve dar as opções de chamadas do método ip ou do método ping com JOptionPane e, dependendo da escolha, instanciar a Classe RedesController.java e chamar o método escolhido. A opção de finalizar a aplicação também deve estar disponível.
### Dicas:
1) Para validar o Sistema Operacional, utilizar a operação contains;
2) Para validar as saídas e executar o que foi pedido, utiizar a operação Split;
3) Processo de chamada de configuração de IP:
- Windows: ```IPCONFIG```
- Linux: ```ifconfig ou ip addr```
4) Processo de chamada de PING com 10 iterações, em IPv4 para www.google.com.br
- Windows: ```ping -4 -n 10 www.google.com.br```
- Linux: ```ping -4 -c 10 www.google.com.br```
---

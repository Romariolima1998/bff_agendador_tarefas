# Agendador de tarefa com notificacao por email

este projeto e composto por quatro microsservicos que sao, usuario para salvar os dados de login e senha no banco de dados postgres, um agendador de tarefa que salva as tarefas em um banco de dados noSQL mongoDB, um notificacao responsavel por notificar por email o usuario e o bff que gerencia todos microsservicos e atraves de um cron busca as tarefas com a respectiva data e hora agendada  no banco  de dados de tempos, e chama o microsservico de notificacao. 

***

### para rodar:

crie uma pasta e dentro dela

`git clone https://github.com/Romariolima1998/usuario.git`

`git clone https://github.com/Romariolima1998/bff_agendador_tarefas.git`

`git clone https://github.com/Romariolima1998/agendador-tarefas.git`

`git clone https://github.com/Romariolima1998/notificacao.git`

abra notificacao navegue ate resource

no arquivo application.yml faca as modificacoes necessaria colocando seu email e senha de app.

abra a pasta bff_agendador_tarefas no terminal e rode o comando:

`docker compose up --build`

***

### requisitos

. git

. Docker

. Docker compose

***
link da documentacao

`http://localhost:8083/swagger-ui/index.html#/`

<img style="display: block;-webkit-user-select: none;margin: auto;cursor: zoom-out;background-color: hsl(0, 0%, 90%);transition: background-color 300ms;" src="https://raw.githubusercontent.com/Romariolima1998/bff_agendador_tarefas/refs/heads/master/images/Captura%20de%20tela%20de%202026-01-23%2012-53-10.png" width="1893" height="772">

<img style="display: block;-webkit-user-select: none;margin: auto;cursor: zoom-in;background-color: hsl(0, 0%, 90%);transition: background-color 300ms;" src="https://raw.githubusercontent.com/Romariolima1998/bff_agendador_tarefas/refs/heads/master/images/Captura%20de%20tela%20de%202026-01-23%2012-53-36.png" width="1365" height="474">


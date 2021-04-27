# Hotmart back-end challenge v1.2.1

## Contextualização

Imagine que você é desenvolvedor de uma empresa de tecnologia e sua nova tarefa é construir uma parte de um marketplace de produtos cujo o número de acesso é alto.

Até o momento você já recebeu a descrição das seguintes entidades:

- Produto: identificador, nome, descrição, data de criação
- Comprador: identificador, nome
- Vendedor: identificador, nome
- Categoria do Produto: identificador, nome
- Venda: identificador, vendedor, comprador, produto

Quando o comprador adquirir um novo produto, ele poderá fazer uma avaliação de 0 a 5. (Não está previamente mapeado nas entidades)

Uma das partes mais importante para o marketplace que está sendo construído será a busca de produtos, cada produto receberá um score para ranqueamento diariamente, esse score deve ser calculado pela fórmula:

 	X = Média de avaliação do produto nos últimos 12 meses
	Y = Quantidade de vendas/dias que o produto existe
	Z = Quantidade de notícias da categoria do produto no dia corrente

Score = X + Y + Z

## O desafio

Sua tarefa consiste em:

- Criar um Crud(create, read, update, delete) de produtos.
- Criar uma API de produtos e disponibilizar as operações de crud (list, find, delete, update, insert).
- Criar um serviço para buscar os produtos ordenados pelo ranqueamento pelo nome e pela categoria.
    - O output do serviço deve conter as informações `dataAtual` e `termoPesquisado`, bem como a lista de produtos que atendem à pesquisa. 
      Os atributos de cada produto retornado são { `identificador`, `nome`, `descrição`, `data de criação`, `score`}
- Todos os serviços devem ser auditados.

## Observações:
-   Para buscar a quantidade de notícias de uma determinada categoria você pode utilizar a api https://newsapi.org/ .
Criar o mapeamento das entidades para atender ao cenário criado.
Crie uma carga inicial para as entidades.

- Considere consumir o endpoint https://newsapi.org/docs/endpoints/top-headlines 4 vezes ao dia (pode usar a estratégia que achar melhor), armazenar a informação no banco local e usar a data de publicação da notícia para fazer a lógica requerida.

	
## O que nós esperamos do seu teste
- Queremos entender sua proficiência técnica, raciocínio analítico (dê preferência para commits curtos e explicativos) e apresentação de resultados. 

- Você não precisa necessariamente fazer tudo que foi proposto porém, queremos ter uma clara percepção sobre como você resolve problemas.

- Para isso fique a vontade para incluir/alterar/remover Entidades, atributos nas entidades existem, tecnologias, jobs, e o que mais sentir necessidade para atender melhor a demanda.

## Mais especificamente na entrega do projeto gostaríamos de ver:

- Documentação.
- Utilização adequada das bibliotecas.
- Código escrito da maneira mais semântica possível, legibilidade importa tanto quanto performance, muitas vezes um não precisa comprometer o outro.
- Desacoplamento e uma clara Hierarquia entre componentes do sistema.
- Testes automatizados.

## O que nós ficaríamos felizes de ver em seu teste
- Tolerância a falha, na chamada da lib externa é possível implementar uma tolerância para deixar o serviço mais robusto.
- Cache - pesquise alguma biblioteca de cache para tornar a busca mais rápida
- Suporte a paginação na listagem dos produtos
- Conteinerização do projeto - Docker
- Estratégia para evitar o consumo excessivo da api de notícias
- Testes automatizados além do unitário
- Utilização de ferramenta para versionamento dos scripts de carga
- Arquitetura da solução, utilização de design patterns adequados e orientação a objetos
- Colocar segurança nos endpoints que persistem dados. (Pode usar usuário/senha em memória)
- Que todo o código esteja em inglês


## O que nós não gostaríamos
- Descobrir que não foi você quem fez seu teste
- Não atendimento aos objetivos propostos.
- Ver um código muito ilegível.
- Não ter documentação com instruções de como rodar o seu código.

## O que avaliaremos de seu teste
- Alcance dos objetivos propostos.
- Semântica, estrutura, legibilidade, manutenibilidade, escalabilidade do seu código e suas tomadas de decisões.
- Organização do projeto.
- Arquitetura da solução, utilização de design patterns adequados e orientação a objetos
- Performance, resiliência e tratamento de erros.
- Documentação da API.
- Modelagem das entidades.


# Pré-requisitos
- Utilização da linguagem Java/Kotlin.
- Não utilizar ferramentas e ou banco de dados que possuem licença comercial.
- Todas tabelas devem ter no mínimo 100 registros.

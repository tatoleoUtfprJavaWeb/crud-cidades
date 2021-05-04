<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crud Cidades</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container-fluid">
        <div class="jumbotron mt-5">
            <h1>Gerenciamento de Cidades</h1>
            <p>Um Crudo para criar, alterar, excluir, editar e listar Cidades</p>
        </div>
        <#if cidadeAtual??>
            <form action="/alterar" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="nomeAtual" value="${(cidadeAtual.nome)!}">
                <input type="hidden" name="estadoAtual" value="${(cidadeAtual.estado)!}">
        <#else>
            <form action="/criar" method="post" class="needs-validation" novalidate>
        </#if>
            <div class="form-group">
                <label for="nome">Cidade:</label>
                <input required type="text" value="${(cidadeAtual.nome)!}${nomeInformado!}" name="nome"
                       class="form-control ${(nome??)?then('is-invalid', '')}" placeholder="Informe o nome da Cidade" id="nome">
                <div class="invalid-feedback">
                    ${nome!}
                </div>
            </div>
            <div class="form-group">
                <label for="estado">Estado:</label>
                <input required maxlength="2" type="text" value="${(cidadeAtual.estado)!}${estadoInformado!}" name="estado"
                       class="form-control ${(estado??)?then('is-invalid', '')}" placeholder="Informe o estado ao qual a cidede pertence" id="estado">
                <div class="invalid-feedback">
                    ${estado!}
                </div>
            </div>
            <#if cidadeAtual??>
                <button type="submit" class="btn btn-primary">Concluir Alteração</button>
            <#else>
                <button type="submit" class="btn btn-primary">Criar</button>
            </#if>
        </form>

        <table class="table table-striped table-hover mt-5">
            <thead class="thead-dark">
                <tr>
                    <td>Nome</td>
                    <td>Estado</td>
                    <td>Ações</td>
                </tr>
            </thead>
            <tbody>
                <#list listaCidades as cidade >
                    <tr>
                        <td>${cidade.nome}</td>
                        <td>${cidade.estado}</td>
                        <td>
                            <div class="d-flex djustify-content-center">
                                <a href="/preparaAlterar?nome=${cidade.nome}&estado=${cidade.estado}" class="btn btn-warning mr-3">Alterar</a>
                                <a href="/excluir?nome=${cidade.nome}&estado=${cidade.estado}" class="btn btn-danger mr-3">Excluir</a>
                            </div>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>

</body>
</html>
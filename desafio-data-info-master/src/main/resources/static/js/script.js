var app = angular.module('crudApp', ['ngMask']);

app.controller('RESTController', function($scope, $http) {

    var getAllUrl = "inicio/listarTodos";

    $scope.updatedElement = {};
    $scope.viewElement = {};

    $scope.listarPessoas = function(){
        $http.get(getAllUrl).then(function (response) {
            $scope.listaPessoa = (response.data);
        }, function error(response) {
            $scope.postResultMessage = "Erro com status: " +  response.statusText;
        });
    }

    $scope.deletarPessoa = function (item, index) {
        var deleteElementUrl = "inicio/deletar/" + item.id;
        $http.delete(deleteElementUrl).then(function () {
            $scope.listaPessoa.splice(index,1);
        });
    }
    
    $scope.adicionarPessoa = function () {
        var pessoaData = {
            primeiroNome : $scope.primeiroNomeForm,
            sobrenome : $scope.sobrenomeForm,
            email : $scope.emailForm,
            cpf: $scope.cpfForm,
            telefone: $scope.telefoneForm
        };

        var url = "inicio/adicionar";

        $http.post(url, pessoaData).then(function (success) {
            console.log(success.data);
            $('#addModal').modal('hide');
            $scope.listaPessoa.push(success.data);
            $scope.primeiroNomeForm='';
            $scope.sobrenomeForm='';
            $scope.emailForm = '';
            $scope.cpfForm='';
            $scope.telefoneForm='';
        });

    }

    $scope.dataTransfer = function (element) {
        $scope.updatedElement.id = element.id;
        $scope.updatedElement.primeiroNome = element.primeiroNome ;
        $scope.updatedElement.sobrenome = element.sobrenome ;
        $scope.updatedElement.email = element.email;
        $scope.updatedElement.cpf = element.cpf;
        $scope.updatedElement.telefone = element.telefone;
    }


    $scope.openUpdate = function (index) {

        var updated = {
        	primeiroNome : $scope.updatedElement.primeiroNome,
        	sobrenome : $scope.updatedElement.sobrenome,
            email : $scope.updatedElement.email,
            cpf : $scope.updatedElement.cpf,
            telefone: $scope.updatedElement.telefone
        };

        var url = "inicio/atualizar/" + $scope.updatedElement.id;

        $http.put(url, updated).then(function (success) {
            console.log(success);
            $('#updateModal').modal('hide');
            $scope.listaPessoa = $scope.listarPessoas();
            $scope.updatedElement.primeiroNome = "";
            $scope.updatedElement.sobrenome = "";
            $scope.updatedElement.email = "";
            $scope.updatedElement.cpf = "";
            $scope.updatedElement.telefone = "";
        });
    }


});
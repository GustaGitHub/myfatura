<template>
    <div class="ms-5 me-5 mt-2 p-5 pb-4" id="form-container">
        <div class="table-responsive">
            <h2 class="text-white fs-1 pb-2">Registro de Faturas</h2>

            <div class="table-responsive">
                <table class="table" >
                    <thead class="table-head">
                        <tr>
                            <th></th>
                            <th>Mês</th>
                            <th>Ano</th>
                            <th>Valor</th>
                        </tr>
                    </thead>
                    <tbody class="table-body" id="table-main" >
                        <tr v-if="faturas.length <= 0">
                            <td colspan="4" class="text-white text-center">
                                Carregando...
                            </td>
                        </tr>
                        <tr v-else v-for="fatura in faturas" :key="fatura.id">
                            <td>
                                <button class="btn btn-danger" v-on:click="deleteFatura(fatura.id)">Excluir</button>
                            </td>
                            <td>{{ ConvertNumberInMonth(fatura.mes) }}</td>
                            <td>{{ fatura.ano }}</td>
                            <td>{{ fatura.valor_fatura != null ? fatura.valor_fatura.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }) : '0,00' }}</td>
                        </tr>
                    </tbody>
                </table>
                
                <div class="d-flex flex-column">
                    <div>
                        <input class="form-control form-control-lg inputs-form" v-model="anoAtual" type="number" placeholder="Digite o ano de registros das faturas" >
                    </div>
                    <div>
                        <button type="submit" v-on:click="getFaturasByYear()"  class="btn mb-3 mt-3 w-100 table-head">Pesquisar Faturas por Ano</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { inject, ref } from 'vue';
    const faturas = inject('faturas')
    const anoAtual = ref(new Date().getFullYear())

    const deleteFatura = (id) => {
        axios.delete('http://localhost:2003/myfatura/' + id)
        .then(() => getFaturasByYear())
        .catch((err) => {
            console.log(err)
            alert('Falha ao excluir fatura, consulte o LOG')
        })
    }

    const getFaturasByYear = () => {
        axios.get('http://localhost:2003/myfatura/ano/'+ anoAtual.value)
        .then(res =>  faturas.value = res.data )
        .catch((err) => console.log(err));
    }

    const ConvertNumberInMonth = (numberMonth) => {
    switch (numberMonth) {
        case "1":
        case 1:
            return "Janeiro";
        case "2":
        case 2:
            return "Fevereiro";
        case "3":
        case 3:
            return "Março";
        case "4":
        case 4:
            return "Abril";
        case "5":
        case 5:
            return "Maio";
        case "6":
        case 6:
            return "Junho";
        case "7":
        case 7:
            return "Julho";
        case "8":
        case 8:
            return "Agosto";
        case "9":
        case 9:
            return "Setembro";
        case "10":
        case 10:
            return "Outubro";
        case "11":
        case 11:
            return "Novembro";
        case "12":
        case 12:
            return "Dezembro";
        default:
            return "Mês inválido"; // Retorno para casos onde o número não corresponde a um mês válido
    }
};


</script>

<style>
.table-head{
    color: white;
    background-color: #8A05BE;
}

.table-body{
    background-color: rgb(55, 55, 55);
    color: white;
}

#table-main{
    border-radius: 25px;
}

</style>

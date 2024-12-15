

<template>
    <div class="ms-5 me-5 mt-2 p-5 pb-4" id="form-container">
        <div>
            <label for="valor_fatura" class="text-white form-label fs-3 ">Valor Da Fatura</label>
            <input class="form-control form-control-lg inputs-form" type="number" placeholder="Valor da Fatura (R$)" aria-label=".form-control-lg example" id="valor_fatura" v-model="valorFatura"/>
        </div>
        <div>
            <label for="mes_ano_fatura" class="text-white form-label fs-3 mt-4 ">Mês/Ano da Fatura </label>
            <input class="form-control form-control-lg inputs-form" type="month" placeholder="Valor da Fatura (R$)" aria-label=".form-control-lg example" id="mes_ano_fatura" v-model="mesAnoFatura">
        </div>
        <div>
            <button class="btn mt-4 w-100 fs-4" id="button-fatura" @click="newFatura()" >
                Registrar fatura 
            </button>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, inject } from 'vue';

const valorFatura = ref("");
const mesAnoFatura = ref("");
var faturas = inject('faturas')

const newFatura = () => {
    if(valorFatura.value.toString().length > 0 && mesAnoFatura.value.length > 0){
        let body = buildBodyRequest();
        requestPostNewFatura(body);
    }
    else{
        alert('Campos invalidos')
    }
}

const buildBodyRequest = () => {
   return {
        valor_fatura: valorFatura.value,
        mes: mesAnoFatura.value.split('-')[1],
        ano: mesAnoFatura.value.split('-')[0]
   }
}

const requestPostNewFatura = (body) => {
    document.body.style.opacity = "0.25";
    axios.post("http://localhost:2003/myfatura", body)
    .then((res) => {
        valorFatura.value = ""
        mesAnoFatura.value = ""
        getFaturasByYear();
    })
    .catch((err) => {
        console.log(err)
        alert('Erro interno ao criar faturas, veja o console')
    })
    document.body.style.opacity = "1";
}


const getFaturasByYear = () => {
        axios.get('http://localhost:2003/myfatura/ano/'+ new Date().getFullYear())
        .then(res =>  faturas.value = res.data )
        .catch((err) => console.log(err));
    }

</script>

<style lang="css">
#form-container{
    background-color: rgb(45, 45, 45);
    border-radius: 10px;
}

.inputs-form, .inputs-form:focus {
    background-color: rgb(55, 55, 55);
    border: none;
    color:white;
}
#button-fatura{
    color: white;
    background-color: #8A05BE;
}

</style>
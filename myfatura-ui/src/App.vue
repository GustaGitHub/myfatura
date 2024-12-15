<script setup>
import Form from './components/Form.vue';
import Table from './components/Table.vue'
import KPI from './components/KPI.vue';
import { onMounted, inject } from 'vue';
import axios from 'axios';

onMounted(() => {
    getFaturasByYear();    
})

let faturas = inject('faturas')

const getFaturasByYear = () => {
    axios.get('http://localhost:2003/myfatura/ano/'+ new Date().getFullYear())
    .then(res =>  faturas.value = res.data )
    .catch((err) => console.log(err));
}

</script>

<template>
  <Form/>
  
  <KPI></KPI>

  <div class="mt-5">
    <Table />
  </div>

 
  
</template>



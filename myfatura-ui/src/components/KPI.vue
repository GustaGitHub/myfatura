<template>
    <div class="alert alert-warning mt-4 ms-5 me-5 mb-5" v-if="faturas == null">
        Carregando dados do KPI...
    </div>
   <Bar class="ms-5 me-5" v-else
    id="my-chart-id"
    :options="{
        responsive: true,
        plugins: {
          legend: {
            labels: {
              color: 'white' // Cor da legenda
            }
          },
          tooltip: {
            titleColor: 'white', // Cor do título do tooltip
            bodyColor: 'white' // Cor do texto do tooltip
          },
          background: {
            backgroundColor: 'white' // Cor de fundo
          }
        },
        scales: {
          x: {
            ticks: {
              color: 'white' // Cor das letras no eixo X
            }
          },
          y: {
            ticks: {
              color: 'white' // Cor das letras no eixo Y
            }
          }
        }
      }"
    :data="{
        labels: getKeyFaturas(),
        datasets: [
          {
            label: 'Faturas em R$ deste ano (' + new Date().getFullYear() + ')', 
            data: getKeyValoresFaturas(),
            backgroundColor: 'purple' // Cor das barras
          }
        ]
      }"/>
</template>

<script setup>
import { inject, watch } from 'vue'
import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)
var faturas = inject('faturas')

const getKeyFaturas = () => {
    let months = []
    faturas.value.map((obj) => months.push(ConvertNumberInMonth(obj.mes)))
    return months;
}

const getKeyValoresFaturas = () => {
    let values = []
    faturas.value.map((obj) => values.push(obj.valor_fatura))
    return values;
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
}

</script>

<style>
#my-chart-id{
    margin-top: 50px;
}

</style>
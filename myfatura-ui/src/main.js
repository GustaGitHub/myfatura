import { createApp, ref } from 'vue'
import '../src/libs/bootstrap/css/bootstrap.min.css'
import './style.css'
import App from './App.vue'

const app = createApp(App)

app.provide('faturas', ref([]));

app.mount('#app')

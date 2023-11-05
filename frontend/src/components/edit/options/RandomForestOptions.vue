<template>
    <v-text-field type="number" label="树的个数 Num of Trees" variant="underlined" v-model="num"></v-text-field>
    <v-text-field type="number" label="深度 Depth (0为不设限)" variant="underlined" v-model="depth"></v-text-field>
    <v-text-field type="number" label="随机种子 Seed" variant="underlined" v-model="seed"></v-text-field>
    <v-text-field type="number" label="特征采样参数 Num of Attributes" variant="underlined" v-model="attriNum"></v-text-field>

</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';


let num: Ref<number> = ref(100)
let depth:Ref<number>=ref(0)
let seed:Ref<number>=ref(1)
let attriNum:Ref<number>=ref(0)

</script>

<style scoped>

</style>
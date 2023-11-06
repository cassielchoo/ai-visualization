<template>
  <router-view></router-view>
</template>

<script setup lang="ts"></script>

<style>
body {
  font-family: HarmonySANS;
  -webkit-user-select: none;
  -moz-user-select: none;
  -o-user-select: none;
  user-select: none;
}

::-webkit-scrollbar {
  width: 5px;
  height: 5px;
  display: none;
}
::-webkit-scrollbar-thumb {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
  background-color: rgba(0, 0, 0, 0.1);
}
::-webkit-scrollbar-track {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
  background-color: rgba(0, 0, 0, 0.1);
}
</style>

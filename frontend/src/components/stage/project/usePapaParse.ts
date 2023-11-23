import VuePapaParse from 'vue-papa-parse';

export default function usePapaParse() {
  function csvToJson() {
    this.$papa.parse('name,address,city,state,zip'); // error, can't read parse of undefined
  }
}

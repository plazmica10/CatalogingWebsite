<template>
  <BooksComp :books="books"></BooksComp>
</template>

<script>

import BooksComp from "@/components/BooksComp.vue";
import axios from "axios";

export default {
    name: "BooksView",
    components:{
      BooksComp,
    },
    data: () => ({
        searchText: "",
        books: [],
    }),
    mounted: function () {
        axios
            .get("http://localhost:9090/books")
            .then((res) => {
            this.books = res.data;
            console.log(this.books);
        })
            .catch((error) => {
            console.log(error);
        });
    },
    methods: {
        SearchBooks: function () {
            this.$router.push({ name: "BooksSearchView", params: { searchText: this.searchText } });
        }
    }
};
</script>
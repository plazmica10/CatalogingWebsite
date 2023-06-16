<template>
  <BooksComp :books="books"></BooksComp>
</template>


<script>
import axios from "axios";
import BooksComp from "@/components/BooksComp.vue";
export default {
    name: "BooksSearchView",
    components: {
      BooksComp,
    },
    data: () => ({
      searchText: '',
      books: [],
    }),

    mounted: function() {
        axios
            // .get(`http://localhost:9090/books/search/${this.$route.params.searchText}`)
            .get(`http://localhost:9090/books/search/${this.$store.state.searchText}`)
            .then((res) => {
                this.books = res.data;
                console.log(this.books);
            })
            .catch((error) => {
            console.log(error);
            });
    },
};

</script>
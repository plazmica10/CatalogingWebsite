<template>
    <table>
      <thead>
      <tr>
        <th>Title</th>
        <th>ISBN</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="book in books" :key="book.id">
        <td>{{ book.title }}</td>
        <td>{{ book.ISBN }}</td>
      </tr>
      </tbody>
    </table>
</template>


<script>
import axios from "axios";
export default {
    name: "BooksSearchView",

    data: () => ({
    books: [],
    }),

    mounted: function() {
        axios
            .get(`http://localhost:9090/books/search/${this.$route.params.searchText}`)
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
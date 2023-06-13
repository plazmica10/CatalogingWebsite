<template>
  <div class="container" style="border: 1px solid red;">
    <h1>Books</h1>

    <div class="row justify-content-center">
      <div class="d-flex justify-content-center">
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
      </div>
    </div>

    <div class="row justify-content-center">
      <form class="w-25 mt-2" role="search">
          <input class="form-control me-2 mb-2" type="search" placeholder="Search" aria-label="Search" v-model="searchText">
          <button class="btn btn-outline-success" type="submit" v-on:click="SearchBooks">Search</button>
        </form>
    </div>
    
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BooksView",

  data: () => ({
    searchText: "",
    books: [],
  }),

  mounted: function() {
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
    SearchBooks: function() {
      this.$router.push({name: "BooksSearchView", params: {searchText: this.searchText}});
    },
  },
};
</script>

<style>
/* Add any necessary styles here */
</style>
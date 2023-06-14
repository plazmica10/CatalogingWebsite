<template>
   <div class="container" style="border: 1px solid red;">
    <h1>Books</h1>
    <div class="row justify-content-center">
      <div class="d-flex justify-content-center">
        <div class="book-container" v-for="book in books" :key="book.id">
          <div class="book">
            <img :src="require('@/assets/' + book.photo)">
            <div class="book-details">
              {{ book.title }}
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- <div class="row justify-content-center">
      <form class="w-25 mt-2" role="search">
          <input class="form-control me-2 mb-2" type="search" placeholder="Search" aria-label="Search" v-model="searchText">
          <button class="btn btn-outline-success" type="submit" v-on:click="SearchBooks">Search</button>
        </form>
    </div> -->
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
    }
  }
};
</script>

<style>
  .book-container {
    display: inline-block;
    margin: 1%;
  }

  .book {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
    border: 1px solid #ccc;
  }

  .book img {
    width: 150px;
    height: 220px;
    margin-bottom: 10px;
  }

  .book-details {
    text-align: center;
    font-size: medium;
    font-weight: bold;
  }
</style>
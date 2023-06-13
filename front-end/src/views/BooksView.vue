<template>
  <div>
    <h1>Books</h1>
    <table>
      <thead>
      <tr>
        <th>Title</th>
        <th>Author</th>
        <th>ISBN</th>
        <th>Price</th>
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
</template>

<script>
import axios from "axios";

export default {
  name: "BooksView",

  data: () => ({
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
};
</script>

<style>
/* Add any necessary styles here */
</style>
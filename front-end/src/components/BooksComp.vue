<template>
    <div class="container">
     <h1>Books</h1>
     <div class="row justify-content-center">
       <div class="d-flex justify-content-center">
         <div class="book-container" v-for="book in books" :key="book.id">
           <div class="book">
             <!-- <img :src="require('@/assets/' + book.photo)"> -->
              <img :src="book.photo">
             <div v-on:click="bookDetails(book)" class="book-details">
               {{ book.title }}
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </template>

<script>
export default {
  name: "BooksComp",
  props: ["books"],
  methods: {
    bookDetails(book) {
      // this.$router.push({name: "book", params: {id: book.id}});
      this.$router.push("/book?id=" + book.id);
    },
  },
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
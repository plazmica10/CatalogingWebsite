<template>
    <div class="wrapper">
        <form class="kanta" @submit.prevent="Add">
        <div class="kanturina">
            <div class="form-group">
                <label>Title</label>
                <input name="name" type="text" class="inputField" v-model="book.title" placeholder="Title" required>
            </div>
            <div class="form-group">
                <label>ISBN</label>
                <input name="isbn" type="text" id="isbn" class="inputField" v-model="book.isbn" pattern="[0-9]{13}" placeholder="13-digit number" required>
            </div>
            <div class="form-group">
                <label>Date</label>
                <input name="date" type="date" class="inputField" v-model="book.date" required>
            </div>
            <div class="form-group">
                <label>Pages</label>
                <input name="username" type="number" class="inputField" v-model="book.pages" required min="1">
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" v-model="book.description" class="inputField"></textarea>
            </div>
            <div class="form-group">
                <label for="photo">Photo:</label>
                <input type="text" id="photo" v-model="book.photo" class="inputField" required placeholder="https://page.com/photo-link">
            </div>
            <div class="form-group">
                <label for="genre">Genre:</label>
                <select id="genre" v-model="book.genre" required>
                    <option v-for="genre in genres" :value="genre">{{ genre.name }}</option>
                </select>
            </div>
        </div>
            <button type="submit" class="button">Add</button>
        </form>
    </div>
</template>
    
<script>
import axios from "axios";

export default{

    data: ()=>({
        book:{
            title:"",
            isbn:"",
            date:"",
            pages:"",
            description:"",
            rating:"",
            photo:"",
            genre:"",
        },
        genres:[],
    }),

    mounted: function(){
        axios
            .get("http://localhost:9090/genres")
            .then((res) => {
                this.genres = res.data;
                console.log(this.genres);
            })
            .catch((error) => {
                console.log(error);
            });
    },
    methods: {
    Add() {
      axios
        .post("http://localhost:9090/books", this.book, {withCredentials: true})
        .then((res) => {
          console.log(res.data);
          this.$router.push({ name: "books" });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
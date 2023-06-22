<template>
    <div class="container" style="border: 1px solid red;">
        <h1>Book</h1>
        <div class="row justify-content-center">
            <div class="d-flex justify-content-center">
                <div class="book-container">
                    <div class="book">
                        <!-- <img :src="require('@/assets/' + this.book.photo)"> -->
                        <!-- <img v-if="isReady" :src="require(this.photoLink)"> -->
                        <img :src="photoLink" alt="">

                        <p>{{ photoLink }}</p>
                        <div class="book-details">
                            <ul>
                                <li>Title: {{ this.book.title }}</li>
                                <li>ISBN: {{ this.book.isbn }}</li>
                                <li>Date: {{ this.book.date }}</li>
                                <li>Pages: {{ this.book.pages }}</li>
                                <li>Description: {{ this.book.description }}</li>
                                <li>Rating: {{ this.book.rating }}</li>
                                <li>Genre: {{ this.genre.name }}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "BookView",
    data: () => ({
        book: {},
        photoLink: "",
        isReady: false,
        genre: {},
    }),
    mounted:async function () {
        axios
            .get(`http://localhost:9090/books/${this.$route.params.id}`)
            .then((res) => {
                // console.log(res.data);
            // this.book = JSON.parse(res.data);
            this.book = res.data;
            this.genre = this.book.genre;
            this.photoLink = '../assets/' + this.book.photo;
            // console.log(this.photoLink);
            // console.log(this.book);
            // console.log(this.genre.name);
            // this.isReady = true;
        })
            .catch((error) => {
            console.log(error);
        });

        // axios
        //     .get(`http:localhost:9090/genres/${this.book.genre}`)
    },
};
</script>

<style>
.book-container {
    display: inline-block;
    margin: 1%;
  }
</style>
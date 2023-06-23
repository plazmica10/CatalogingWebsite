<template>
    <div class="container" style="border: 1px solid red;">
        <h1>Book</h1>
        <div class="row justify-content-center">
            <div class="d-flex justify-content-center">
                <div class="book-container">
                    <div class="book">
                        <img :src="book.photo">
                        <button class="btn btn-success" v-on:click="showAddToShelf()">Add to shelf</button>
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

    <dialog class="" id="addToShelf">
        <h1>Add To Shelf</h1>
        <form>
            <select v-model="selectedShelfId">
                <option v-for="shelf in shelves" :key="shelf.id" :value="shelf.id">{{ shelf.name }}</option>
            </select>
            <button type="button" @click="addToShelf(selectedShelfId)">Add to Shelf</button>
            <button type="button" @click="closeAddToShelf">Cancel</button>
        </form>
    </dialog>
</template>

<script>
import axios from "axios";

export default {
    name: "book",
    data: () => ({
        book: {
            title: "",
            ISBN: "",
            date: "",
            pages: "",
            description: "",
            rating: "",
            photo: "",
            genre: "",
        },
        shelves: [],
        selectedShelfId: null,
        photoLink: "",
        genre: {},
        id: 0,
    }),
    mounted:async function () {
        this.id = this.$route.params.id;

        await this.refresh();

        const addToShelfModal = document.getElementById("addToShelf");

        addToShelfModal.addEventListener("click", (e) => {
            const dialogDimensions = addToShelfModal.getBoundingClientRect();
            if (e.clientX < dialogDimensions.left || e.clientX > dialogDimensions.right || e.clientY < dialogDimensions.top || e.clientY > dialogDimensions.bottom) {
                addToShelfModal.close();
            }
        });
    },

    methods: {
        async refresh() {
            axios
                .get("http://localhost:9090/books/" + this.id, { withCredentials: true })
                .then((res) => {
                // console.log(res.data);
                this.book = res.data;
                this.genre = this.book.genre;
                this.photoLink = '../assets/' + this.book.photo;
            })
                .catch((error) => {
                console.log(error);
            });

            axios
                .get("http://localhost:9090/shelves", { withCredentials: true })
                .then((res) => {
                this.shelves = res.data;
                console.log(this.shelves);
            })
                .catch((error) => {
                console.log(error);
            });
        },

        addToShelf(shelfId) {
            // console.log("add to shelf: ", shelfId);

            axios
                .post("http://localhost:9090/shelfItems/" + this.id + "/" + shelfId, {}, { withCredentials: true })
                .then((res) => {
                console.log(res.data);
                this.refresh();
            })
                .catch((error) => {
                console.log(error);
                alert("Could not add book to shelf.");
            });

            this.closeAddToShelf();
        },

        showAddToShelf() {
            document.getElementById("addToShelf").showModal();
        },
        closeAddToShelf() {
            document.getElementById("addToShelf").close();
        },
    }
};
</script>

<style>
.book-container {
    display: inline-block;
    margin: 1%;
  }
</style>
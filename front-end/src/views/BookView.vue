<template>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
    <div class="container">
        <h1>Book</h1>
        <div class="row justify-content-center">
            <div class="d-flex justify-content-center">
                <div class="book-container">
                    <div class="book">
                        <img :src="book.photo">
                        <div class="d-flex justify-content-between">
                            <button v-if="this.$store.state.loggedIn && this.$store.state.user.role != 'ADMIN'" class="btn btn-success" v-on:click="showAddToShelf()" style="margin:5px; padding: 5px">Add to shelf</button>
                            <div>
                                <button v-if="this.$store.state.loggedIn && this.$store.state.user.role != 'READER'" class="btn btn-secondary" v-on:click="showUpdateBook(book.id)" style="margin:5px; padding: 5px">Edit</button>
                                <button v-if="this.$store.state.loggedIn && this.$store.state.user.role == 'ADMIN'" class="btn btn-danger" v-on:click="deleteBook()" style="margin:5px; padding: 5px">Delete</button>
                            </div>
                        </div>
                        <div class="book-details">
                            <ul class="list-group">
                                <li class="list-group-item">Title: {{ this.book.title }}</li>
                                <li class="list-group-item">ISBN: {{ this.book.isbn }}</li>
                                <li class="list-group-item">Date: {{ this.book.date }}</li>
                                <li class="list-group-item">Pages: {{ this.book.pages }}</li>
                                <li class="list-group-item">Description: {{ this.book.description }}</li>
                                <li class="list-group-item">Rating: {{ this.book.rating }}</li>
                                <li class="list-group-item">Genre: {{ this.genre.name }}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            <div class="reviews">
                <h2>Reviews</h2>
                <div class="kutija" v-for="review in shelfItems.reviews" :key="review.id">
                        <p><b>{{review.user.username}}</b> says: {{review.comment}}</p>
                        <div class="starss">
                            <i class="fa-solid fa-star" v-if="review.rating >=1"></i>
                            <i class="fa-solid fa-star" v-if="review.rating >=2"></i>
                            <i class="fa-solid fa-star" v-if="review.rating >=3"></i>
                            <i class="fa-solid fa-star" v-if="review.rating >=4" ></i>
                            <i class="fa-solid fa-star" v-if="review.rating >=5"></i>
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

    <dialog id="updateBookModal">
        <h1>Update Book</h1>
        <form @submit.prevent="updateBook">
            <input type="text" v-model="newTitle" placeholder="Title" >
            <input type="text" v-model="newISBN" placeholder="ISBN" >
            <input type="text" v-model="newDate" placeholder="Date" >
            <input type="text" v-model="newPhoto" placeholder="Photo" >
            <button type="submit">Update</button>
        </form>
        <button v-on:click="closeUpdateShelf()">Close</button>
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
        newTitle: "",
        newISBN: "",
        newDate: "",
        newPhoto: "",
        shelves: [],
        shelfItems: [
            {
                book:null,
                reviews: [],
            }
        ],
        selectedShelfId: null,
        photoLink: "",
        genre: {},
        id: 0,
    }),
    mounted:async function () {
        // this.id = this.$route.params.id;
        this.id = this.$route.query.id;

        await this.refresh();

        const addToShelfModal = document.getElementById("addToShelf");
        const updateBookModal = document.getElementById("updateBookModal");

        addToShelfModal.addEventListener("click", (e) => {
            const dialogDimensions = addToShelfModal.getBoundingClientRect();
            if (e.clientX < dialogDimensions.left || e.clientX > dialogDimensions.right || e.clientY < dialogDimensions.top || e.clientY > dialogDimensions.bottom) {
                addToShelfModal.close();
            }
        });

        updateBookModal.addEventListener("click", (e) => {
            const dialogDimensions = updateBookModal.getBoundingClientRect();
            if (e.clientX < dialogDimensions.left || e.clientX > dialogDimensions.right || e.clientY < dialogDimensions.top || e.clientY > dialogDimensions.bottom) {
                updateBookModal.close();
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

            axios
                .get("http://localhost:9090/shelfItems/" + this.id, { withCredentials: true })
                .then((res) => {
                    this.shelfItems = res.data;
                     console.log(this.shelfItems);
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
        showUpdateBook() {
            document.getElementById("updateBookModal").showModal();
        },
        closeUpdateShelf() {
            document.getElementById("updateBookModal").close();
        },
        deleteBook(){
            axios
                .delete("http://localhost:9090/books/" + this.id, { withCredentials: true })
                .then((res) => {
                console.log(res.data);
                this.$router.push("/books");
            })
                .catch((error) => {
                console.log(error);
                alert("Could not delete book.");
            });
        },
        updateBook(){
            if (this.newTitle.trim() === '' && this.newISBN.trim() === '' && this.newDate.trim() === '' && this.newPhoto.trim() === '') {
                return; // If all fields are empty, do not send the request
            }

            const updatedBook = {
                title: this.newTitle.trim() !== '' ? this.newTitle.trim() : this.book.title,
                isbn: this.newISBN.trim() !== '' ? this.newISBN.trim() : this.book.isbn,
                date: this.newDate.trim() !== '' ? this.newDate.trim() : this.book.date,
                photo: this.newPhoto.trim() !== '' ? this.newPhoto.trim() : this.book.photo,
            };

            axios
                .put("http://localhost:9090/books/"+ this.id,updatedBook, { withCredentials: true })
                .then((res) => {
                console.log(res.data);
                this.refresh();
            })
                .catch((error) => {
                console.log(error);
                alert("Could not update book.");
            });

            this.closeUpdateShelf();
        }
    }
};
</script>

<style>
.book-container {
    display: inline-block;
    margin: 1%;
  }
.reviews{
    display: inline-block;
    margin: 1%;
}
.kutija{
    border: 1px solid black;
    padding: 10px;
    margin: 10px;
    border-radius: 15px;
}
.starss i{
    color: #ff9c1a;
    font-size: 20px;
}
</style>
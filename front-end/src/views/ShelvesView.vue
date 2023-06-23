<template>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />

    <div class="container">
     <h1>Shelves</h1>
     <div style="margin-bottom: 0.7em;">
          <button class="btn btn-primary" v-on:click="showAddShelf()">Add Shelf</button>
     </div>
     <div v-for="shelf in shelves" :key="shelf.id">
        <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                {{ shelf.name }}

                <span>
                    <button v-if="shelf.isPrimary === false" class="btn btn-warning" style="margin-right: 0.7em;" v-on:click="showUpdateShelf(shelf.id)">Update</button>
                    <button v-if="shelf.isPrimary === false" class="btn btn-danger" style="margin-right: 0.7em;" @click="deleteShelf(shelf.id)">Delete</button>
                    <span class="badge rounded-pill text-bg-primary">{{ shelf.shelfItems.length }}</span>
                </span>
            </li>
            <ul class="list-group mx-4">
                <li v-for="shelfItem in shelf.shelfItems" :key="shelfItem.id" class="list-group-item list-group-item-light d-flex justify-content-between align-items-center">
                    {{ shelfItem.book.title }}

                    <span>
                        <button v-if="shelf.name === 'Read'" class="btn btn-warning btn-sm" style="margin-right: 0.6em;" v-on:click="showRateBook(shelfItem.id)">Rate</button>
                        <button class="btn btn-danger btn-sm" @click="deleteShelfItem(shelfItem.id, shelf.id)">Delete</button>
                    </span>
                </li>
            </ul>
        </ul>
     </div>
   </div>

   <dialog class="" id="addShelfModal">
        <h1>Add Shelf</h1>
        <form @submit.prevent="addShelf">
            <input type="text" v-model="newShelf.name" placeholder="Shelf name" required>
            <button type="submit">Create</button>
        </form>
        <button v-on:click="closeAddShelf()">Close</button>
    </dialog>

    <dialog id="updateShelfModal">
        <h1>Update Shelf</h1>
        <form @submit.prevent="updateShelf">
            <input type="text" v-model="newName" placeholder="Shelf name" required>
            <button type="submit">Update</button>
        </form>
        <button v-on:click="closeUpdateShelf()">Close</button>
    </dialog>

    <dialog id="rateBookModal" class="rating-box">
        <header class="text-secondary">How did you like the book?</header>
        <div class="stars">
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
        </div>
        <form action="">
            <textarea style="margin-top: 1em; border-radius: 25px; padding: 0.7em;" v-model="review" name="" id="" cols="30" rows="10" placeholder="Write a review..."></textarea>
        </form>
        <button class="btn btn-success" v-on:click="rateBook()">Rate</button>
    </dialog>
 </template>
 
 <script>
 import axios from "axios";

 
 
 export default {
   name: "ShelvesView",
 
   data: () => ({
        idForUpdate: 0,
        idForReview: 0,
        newName: "",
        newShelf: {
            name: "",
        },
        shelves: [],
        rating: 0,
        review: "",
   }),
 
   mounted: function() {
        this.fetchShelves();

        const addShelfModal = document.getElementById("addShelfModal");
        const updateShelfModal = document.getElementById("updateShelfModal");
        const rateBookModal = document.getElementById("rateBookModal");

        addShelfModal.addEventListener("click", (e) => {
            const dialogDimensions = addShelfModal.getBoundingClientRect();
            if (e.clientX < dialogDimensions.left || e.clientX > dialogDimensions.right || e.clientY < dialogDimensions.top || e.clientY > dialogDimensions.bottom) {
                addShelfModal.close();
            }
        });

        updateShelfModal.addEventListener("click", (e) => {
            const dialogDimensions = updateShelfModal.getBoundingClientRect();
            if (e.clientX < dialogDimensions.left || e.clientX > dialogDimensions.right || e.clientY < dialogDimensions.top || e.clientY > dialogDimensions.bottom) {
                updateShelfModal.close();
            }
        });

        rateBookModal.addEventListener("click", (e) => {
            const dialogDimensions = rateBookModal.getBoundingClientRect();
            if (e.clientX < dialogDimensions.left || e.clientX > dialogDimensions.right || e.clientY < dialogDimensions.top || e.clientY > dialogDimensions.bottom) {
                rateBookModal.close();
            }
        });

        const stars = document.querySelectorAll(".stars i");
        // Loop through the "stars" NodeList
        stars.forEach((star, index1) => {
        // Add an event listener that runs a function when the "click" event is triggered
        star.addEventListener("click", () => {
            this.rating = index1 + 1;
            // Loop through the "stars" NodeList Again
            stars.forEach((star, index2) => {
            // Add the "active" class to the clicked star and any stars with a lower index
            // and remove the "active" class from any stars with a higher index
            index1 >= index2 ? star.classList.add("active") : star.classList.remove("active");
            });
        });
        });
   },

   methods: {
        fetchShelves() {
            axios
         .get("http://localhost:9090/shelves", {withCredentials: true})
         .then((res) => {
            this.shelves = res.data;
            // console.log(this.shelves);

            this.shelves.forEach(shelf => {
                axios
                .get("http://localhost:9090/shelves/" + shelf.id, {withCredentials: true})
                .then((res) => {
                    shelf.shelfItems = res.data.shelfItems;
                    // console.log(shelf.shelfItems);
                })
                .catch((error) => {
                    console.log(error);
                });
            });
         })
         .catch((error) => {
           console.log(error);
         });
        },

        showAddShelf: function() {
            const addShelfModal = document.getElementById("addShelfModal");
            addShelfModal.showModal();
        },
        closeAddShelf: function() {
            const addShelfModal = document.getElementById("addShelfModal");
            addShelfModal.close();
        },

        showUpdateShelf: function(idSh) {
            this.idForUpdate = idSh;
            const updateShelfModal = document.getElementById("updateShelfModal");
            updateShelfModal.showModal();
        },
        closeUpdateShelf: function() {
            const updateShelfModal = document.getElementById("updateShelfModal");
            updateShelfModal.close();
        },

        showRateBook: function(idShi) {
            this.idForReview = idShi;
            const rateBookModal = document.getElementById("rateBookModal");
            rateBookModal.showModal();
        },
        closeRateBook: function() {
            const rateBookModal = document.getElementById("rateBookModal");
            rateBookModal.close();
        },

        addShelf: function() {
            axios
                .post("http://localhost:9090/shelves", this.newShelf, {withCredentials: true})
                .then((res) => {
                    console.log(res.data);
                    this.shelves.push(res.data);
                    this.fetchShelves();
                })
                .catch((error) => {
                    console.error(error);
                    this.error = 'An error occurred while adding the shelf.';
                    alert("Could not create shelf.");
                });
        },

        deleteShelf: function(id) {
            axios
                .delete("http://localhost:9090/shelves/" + id, {withCredentials: true})
                .then((res) => {
                    // console.log(res.data);
                    this.fetchShelves();
                })
                .catch((error) => {
                    console.error(error);
                    this.error = 'An error occurred while adding the shelf.';
                    alert("Could not delete shelf.");
                });
        },

        updateShelf: function(id) {
            axios
                .put("http://localhost:9090/shelves/" + this.idForUpdate, {name: this.newName}, {withCredentials: true})
                .then((res) => {
                    console.log(res.data);
                    this.fetchShelves();
                })
                .catch((error) => {
                    console.error(error);
                    this.error = 'An error occurred while updateing the shelf.';
                    alert("Could not update shelf.");
                });
        },

        rateBook() {
            console.log("Rating: " + this.rating + ". Review: " + this.review + ".");
                console.log("ID1: " + this.idForReview + ".");
            axios
                .post("http://localhost:9090/reviews/" + this.idForReview, {rating: this.rating, comment: this.review}, {withCredentials: true})
                .then((res) => {
                    console.log(res.data);
                    this.fetchShelves();
                })
                .catch((error) => {
                    // console.error(error);
                    this.error = 'An error occurred while adding the review.';

                    if (this.rating != null || this.review != null) {
                        console.log("ID2: " + this.idForReview + ".");
                        axios
                        .put("http://localhost:9090/reviews/" + this.idForReview, {rating: this.rating, comment: this.review}, {withCredentials: true})
                        .then((res) => {
                            console.log(res.data);
                            this.fetchShelves();
                        })
                        .catch((error) => {
                            console.error(error);
                            console.log(error.data);
                            this.error = 'An error occurred while adding the review.';
                            alert("Could not add review.");
                        });
                    }
                    
                });

            this.closeRateBook();
        },

        deleteShelfItem(shiId, shId) {
            console.log("Shelf item id: " + shiId + ". Shelf id: " + shId + ".");
            axios
                .delete("http://localhost:9090/shelfItems/" + shiId + "/" + shId, {withCredentials: true})
                .then((res) => {
                    // console.log(res.data);
                    this.fetchShelves();
                })
                .catch((error) => {
                    console.error(error);
                    this.error = 'An error occurred while adding the shelf.';
                    alert("Could not delete shelf item.");
                });
        }
   }
 };
 </script>
 
 <style>
.dialog::backdrop {
    background-color: rgba(0, 0, 0, 0.548);
}

@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");
* {
  font-family: "Poppins", sans-serif;
}
.rating-box {
  background: linear-gradient(45deg, #ffd195, #ffb283);
  padding: 25px 50px 35px;
  border-radius: 25px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.05);
}
.rating-box header {
  font-size: 22px;
  color: #dadada;
  font-weight: 500;
  margin-bottom: 20px;
  text-align: center;
}
.rating-box .stars {
  display: flex;
  align-items: center;
  gap: 25px;
}
.stars i {
  color: white;
  font-size: 35px;
  cursor: pointer;
  transition: color 0.2s ease;
}
.stars i.active {
  color: #ff9c1a;
}
 </style>
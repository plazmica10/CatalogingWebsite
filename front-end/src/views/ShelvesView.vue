<template>
    <div class="container" style="border: 1px solid red;">
     <h1>Shelves</h1>
     <div>
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
                </li>
            </ul>
        </ul>
     </div>
   </div>

   <dialog class="" id="addShelfModal">
        <form @submit.prevent="addShelf">
            <input type="text" v-model="newShelf.name" placeholder="Shelf name" required>
            <button type="submit">Create</button>
        </form>
        <button v-on:click="closeAddShelf()">Close</button>
    </dialog>

    <dialog id="updateShelfModal">
        <form @submit.prevent="updateShelf">
            <input type="text" v-model="newName" placeholder="Shelf name" required>
            <button type="submit">Update</button>
        </form>
        <button v-on:click="closeUpdateShelf()">Close</button>
    </dialog>
 </template>
 
 <script>
 import axios from "axios";

 
 
 export default {
   name: "ShelvesView",
 
   data: () => ({
        idForUpdate: 0,
        newName: "",
        newShelf: {
            name: "",
        },
        shelves: [],
   }),
 
   mounted: function() {
        this.fetchShelves();

        const addShelfModal = document.getElementById("addShelfModal");
        const updateShelfModal = document.getElementById("updateShelfModal");

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

        addShelf: function() {
            axios
                .post("http://localhost:9090/shelves", this.newShelf, {withCredentials: true})
                .then((res) => {
                    console.log(res.data);
                    this.shelves.push(res.data);
                })
                .catch((error) => {
                    console.error(error);
                    this.error = 'An error occurred while adding the shelf.';
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
                });
        }
   }
 };
 </script>
 
 <style>
.dialog::backdrop {
    background-color: rgba(0, 0, 0, 0.548);
}
 </style>
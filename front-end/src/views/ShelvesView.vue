<template>
    <div class="container" style="border: 1px solid red;">
     <h1>Shelves</h1>
     <div>
          <button class="btn btn-primary" @click="showDialog = true; console.log(showDialog)">Add Shelf</button>
          <dialog class="" style="z-index: 9999;" v-if="this.showDialog" aria-modal="true" role="dialog" open>
            <form @submit.prevent="addShelf">
                <input type="text" v-model="newShelf.name" placeholder="Shelf name" required>
                <button type="submit">Send</button>
            </form>
            <button @click="showDialog = false">Close</button>
          </dialog>
     </div>
     <div v-for="shelf in shelves" :key="shelf.id">
        <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                {{ shelf.name }}
                <span>
                    <button class="btn btn-danger" style="margin-right: 0.7em;" @click="deleteShelf(shelf.id)">Delete</button>
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
 </template>
 
 <script>
 import axios from "axios";
 
 export default {
   name: "ShelvesView",
 
   data: () => ({
        showDialog: false,
        newShelf: {
            name: "",
        },
        shelves: [],
   }),
 
   mounted: function() {
     this.fetchShelves();
   },

   methods: {
        fetchShelves() {
            axios
         .get("http://localhost:9090/shelves", {withCredentials: true})
         .then((res) => {
            this.shelves = res.data;
            console.log(this.shelves);

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
        }
   }
 };
 </script>
 
 <style>
 </style>
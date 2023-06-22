<template>
    <div class="container" style="border: 1px solid red;">
     <h1>Shelves</h1>
     <div>
          <button class="btn btn-primary" @click="showDialog = true; console.log(showDialog)">Add Shelf</button>
          <dialog style="modal-dialog modal-dialog-centered" v-if="this.showDialog" role="dialog">
            <p>kita</p>
            <button @click="showDialog = false">Close</button>
          </dialog>
     </div>
     <div v-for="shelf in shelves" :key="shelf.id">
        <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                {{ shelf.name }}
                <span class="badge rounded-pill text-bg-primary">{{ shelf.shelfItems.length }}</span>
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
        shelves: [],
   }),
 
   mounted: function() {
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

   methods: {
        addShelf: function() {
            axios
                .post("http://localhost:9090/shelves", this.inShelf, {withCredentials: true})
                .then((res) => {
                    console.log(res.data);
                    this.shelves.push(res.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
   }
 };
 </script>
 
 <style>
 </style>
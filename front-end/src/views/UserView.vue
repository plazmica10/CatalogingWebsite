<!-- <template>
<div class="container" style="border: 1px solid red;">
    <h1>Shelves</h1>
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
  name: "UserView",

  data: () => ({
    inShelf: {
      id: 0,
      isPrimary: false,
      name: "",
      shelfItems: []
    },
    shelves: [],
    users: [],
  }),
  mounted: function() {
    axios
        .get("http://localhost:9090/users/"+ this.$route.params.id, {withCredentials: true})
        .then((res) => {
          this.users = res.data;
          console.log(this.users);
        })
        .catch((error) => {
          console.log(error);
        });
    axios
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
  }
};
</script> -->
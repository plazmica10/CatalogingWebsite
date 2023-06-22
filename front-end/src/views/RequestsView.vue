<template>
    <div>
        <h1>Requests</h1>
        <div class="okvir" v-for="request in requests" :key="request.id">
            {{ request.email}}
            {{ request.user.name }}
            {{ request.user.surname }}
            <button class="btn btn-success" name="approve" style="margin: 5px" @click="approveRequest(request.id)">Approve</button>
            <button class="btn btn-danger" name="deny" style="margin: 5px" @click="denyRequest(request.id)">Deny</button>
        </div>
    </div>
</template>
    

<script>
import axios from "axios";

export default{
    name:"RequestsView",

    data: ()=>({
        requests:[{
            email:"",
            user:{
                name:"",
                surname:""
            }
        }]
    }),

    mounted: function () {
    axios
      .get("http://localhost:9090/requests", { withCredentials: true })
      .then((res) => {
        this.requests = res.data;
        console.log(this.requests);
      })
      .catch((error) => {
        console.log(error);
      });
    },

    methods: {
        approveRequest: async function (id) {
            try {
                const res = await axios.post(`http://localhost:9090/requests/${id}/approve`,null,{ withCredentials: true });
                console.log(res);
                // Refresh the requests list after approving the request
                await this.refreshRequests();
            } catch (error) {
                console.log(error);
            }
        },

    denyRequest: async function (id) {
      try {
        const res = await axios.delete(
          `http://localhost:9090/requests/${id}/deny`,
          { withCredentials: true }
        );
        console.log(res);
        // Refresh the requests list after denying the request
        await this.refreshRequests();
      } catch (error) {
        console.log(error);
      }
    },

    refreshRequests: async function () {
      try {
        const res = await axios.get("http://localhost:9090/requests", {withCredentials: true});
        this.requests = res.data;
        console.log(this.requests);
      } catch (error) {
        console.log(error);
      }
    },
  },
}

</script>

<style>

.okvir{
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid black;
    margin: 10px;
    padding: 10px;
}
</style>
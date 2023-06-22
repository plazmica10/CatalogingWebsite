<template>
    <div class="container" style="border: 1px solid red;">
        <h1>Book</h1>
        <div class="row justify-content-center">
            <div class="d-flex justify-content-center">
                <div class="book-container">
                    <div class="book">
                        <!-- <img :src="require('@/assets/' + book.photo)"> -->
                        <div class="book-details">
                            <!-- {{ book.title }} -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: "BookView",
    data: () => ({
        book: {},
    }),
    mounted: function () {
        axios
            .get(`http://localhost:9090/books/${this.$route.params.id}`)
            .then((res) => {
                console.log(res.data);
            this.book = res.data;
            console.log(this.book);
        })
            .catch((error) => {
            console.log(error);
        });
    },
};
</script>

<style>
.book-container {
    display: inline-block;
    margin: 1%;
  }
</style>
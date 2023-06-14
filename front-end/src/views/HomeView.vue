<template>
  <div class="home-view">
  <p class="paragraf">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Maiores natus, ad libero expedita adipisci, voluptates exercitationem voluptas, ipsam nesciunt odio praesentium molestiae recusandae cum? Delectus similique accusantium eius magni amet?
  Et eius sit fugiat ullam itaque vitae quaerat totam explicabo dolores animi laboriosam quam iusto voluptas aperiam dolorem corporis eligendi, perspiciatis minus minima cupiditate rem maxime laborum? Iusto, molestias reprehenderit.
  Odio, asperiores dignissimos. Labore fugit laboriosam alias temporibus molestias. Excepturi, illo laboriosam mollitia quasi quidem quis nesciunt! Culpa maiores error fuga porro, esse earum ipsa tempore sapiente, commodi velit minus.
  </p>
</div>
</template>

<script>
export default {
  name: "HomeView",
};
</script>

<style>

.home-view {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
  padding: 0;
  height: 95vh;
  background-image:linear-gradient(rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5)),url("../assets/cover.jpg");
  border: 1px solid red;
  background-size: cover;
}

.home-view .paragraf{
  width: 50%;
  font-family: "Montserrat"; 
}
</style>
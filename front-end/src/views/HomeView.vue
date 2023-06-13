<template>
  <h1>Welcome</h1>

  <form class="container text-center" style="width: 30%">
    <div class="mb-3">
      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email">
      <div id="emailHelp" class="form-text">We'll never share your email with anyone else.(We will sell it)</div>
    </div>
    <div class="mb-3">
      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
    </div>
    <button type="submit" class="btn btn-primary" v-on:submit="">Submit</button>
  </form>
</template>

<script>
export default {
  name: "HomeView",
};
</script>

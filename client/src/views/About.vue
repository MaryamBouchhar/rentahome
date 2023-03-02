<template>
  <div class="hero h-[240px]" style="background-image: url('../../../src/assets/hero_img.jpeg')">
    <div class="hero-overlay bg-opacity-60"></div>
    <div class="hero-content text-center text-neutral-content">
      <div class="max-w-md">
        <h1
            class="mb-5 text-5xl font-bold"
        >
            {{ $t('about.our_story') }}
        </h1>
        <div class="flex justify-center space-x-2 mb-5">
<<<<<<< HEAD
          <div class="badge badge-outline">Most trusted</div>
          <div class="badge badge-outline">Best prices</div>
          <div class="badge badge-outline">Modern design</div>
          <div class="badge badge-outline">{{ message }}</div>
=======
          <div class="badge badge-outline">{{ $t('tags.most_trusted') }}</div>
          <div class="badge badge-outline">{{ $t('tags.best_prices') }}</div>
          <div class="badge badge-outline">{{ $t('tags.modern_design') }}</div>
>>>>>>> bd5286dabf0029fc79ffbe1b802c6a2c154526b2
        </div>
      </div>
    </div>
  </div>

  <!-- about content -->
  <div class="container mx-auto">
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-8 lg:gap-6">
      <div class="flex flex-col justify-center items-center">
        <img src="../../src/assets/our_story.png" alt="about image" class="w-full h-full object-cover"/>
      </div>
      <div class="flex flex-col justify-center items-center">
        <h1 class="text-3xl font-bold">{{ $t('about.our_story') }}</h1>
        <p class="py-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam, aspernatur atque autem
          <consequuntur></consequuntur>
          cumque delectus doloremque doloribus ducimus earum enim eos esse et eveniet exercitationem fugiat
          impedit
          incidunt inventore ipsa ipsam iure iusto laboriosam magnam magni maxime minima molestiae natus
          necessitatibus
          nemo neque nobis non nulla officia omnis optio pariatur perspiciatis placeat porro praesentium
          provident
        </p>
        <p class="py-2">
          quae quas quasi quibusdam quidem quisquam quod ratione recusandae repellat reprehenderit repudiandae
          rerum
          saepe sapiente sed sequi sint soluta suscipit tempora temporibus tenetur totam ullam unde ut
          veritatis
          voluptas voluptatem voluptates voluptatibus voluptatum. Accusamus adipisci alias aliquid amet
          architecto
          asperiores atque autem beatae blanditiis commodi consequatur consequuntur corporis cumque cupiditate
          debitis
          deleniti deserunt dignissimos dolorem doloremque doloribus ducimus ea earum eius enim eos error
          est
        </p>
        <p class="py-2">
          excepturi expedita explicabo facilis fuga fugiat fugit harum hic id illum impedit in incidunt
          inventore
          ipsa ipsam iure iusto laboriosam laborum laudantium libero magnam magni maxime minima molestiae
          molestias
          mollitia nam natus nemo neque nobis non nulla numquam odio officia omnis optio pariatur perspiciatis
          placeat
          porro praes
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "About",
  data() {
    return {
      properties: [],
      message: ''
    };
  },
  methods: {
    async getProperties() {

      const data = {
        name: "test",
        email: "test@gmail.com",
        phone: "123456789",
        password: "test"
      };

      axios.post('http://localhost:8080/post', data)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          });

      axios.delete('http://localhost:8080/delete/1')
          .then(response => {
            this.message = response.data;
          })
          .catch(error => {
            console.log(error);
          });

      axios.get('http://localhost:8080/hello')
          .then(response => {
            this.message = response.data;
          })
          .catch(error => {
            console.log(error);
          });
    },
  },
  mounted() {
    this.getProperties();
  },
};
</script>

<style scoped>

</style>
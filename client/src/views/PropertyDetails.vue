<template>
  <div>
    <div class="flex flex-col lg:flex-row my-5">
      <div class="property-images px-4">
        <div class="carousel w-full" id="images">
          <div :id="`image`+image.id" class="carousel-item w-full" v-for="image in property.images" :key="image">
            <img :src="image.url" class="w-full"/>
          </div>
        </div>
        <div class="flex justify-center w-full py-2 gap-2">
          <a :href="`#image`+index" class="btn btn-xs" v-for="index in images_count" :key="index"> {{ index }} </a>
        </div>
        <!-- comments section -->
        <h1 class="text-2xl font-bold mb-3">Comments</h1>
        <div class="chat chat-start" v-for="comment in comments" :key="comment.id">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full">
              <img :src="comment.user.avatar" alt="avatar" v-if="comment.user.avatar !== null"/>
              <img src="https://cdn-icons-png.flaticon.com/512/149/149071.png" alt="avatar" v-else/>
            </div>
          </div>
          <div class="chat-bubble">
            <div class="chat-bubble-content">
              <div class="chat-bubble-content-text">
                {{ comment.content }}
              </div>
              <div class="chat-bubble-content-time text-xs mt-1 font-light">
                {{ comment.created_at }}
              </div>
            </div>
          </div>
        </div>

        <!-- add comment -->
        <div class="flex flex-col mt-3">
          <h1 class="text-2xl font-bold mb-3">Rate this property</h1>

          <textarea
              class="textarea h-24 textarea-secondary mb-3"
              placeholder="Your comment"
              v-model="comment.content"
              @change="comment.content = $event.target.value;"
          ></textarea>

          <div class="rating">
            <input type="radio" name="rating-2" :checked="index === selected" @change="select(index)"
                   class="mask mask-star-2 bg-orange-400" v-for="(star, index) in stars" :key="index">
          </div>
          <button @click="addComment" class="btn btn-wide mt-3">Add Comment</button>
        </div>
      </div>
      <div class="property-details w-full mx-4">
        <h1 class="text-3xl font-bold mb-3"> {{ property.title }} </h1>
        <div class="flex flex-row items-center gap-2 mb-3">
          <div class="rating rating-sm">
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
          </div>
          <span class="rate-count">()</span>
        </div>
        <p class="mb-3">{{ property.description }}</p>
        <h1 class="font-bold text-xl text-green-400 mb-3">${{ property.price }} </h1>
        <div class="overflow-x-auto mb-3">
          <table class="table w-full">
            <tbody>
            <!-- row 1 -->
            <tr>
              <th class="font-bold text-lg">Category</th>
              <td>{{ property.category }}</td>
            </tr>
            <!-- row 2 -->
            <tr class="active">
              <th class="font-bold text-lg">Rent type</th>
              <td>{{ property.rent_type }}</td>
            </tr>
            <!-- row 3 -->
            <tr>
              <th class="font-bold text-lg">Rooms count</th>
              <td>{{ property.room_count }}</td>
            </tr>
            </tbody>
          </table>
        </div>
        <!--maps -->
        <div id="embed-map"></div>
        <div class="flex justify-center">
          <button class="btn btn-wide " v-if="property.status=='Available'">Book now</button>
          <button v-else disabled class="btn btn-wide cursor-not-allowed opacity-70 "
                  title="this property is Rented in this period">Book now
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {useStore} from "vuex";
import {computed} from "vue";

export default {
  name: "PropertyDetails",
  setup() {
    const store = useStore();

    return {
      rtl: computed(() => store.state.rtl),
      user: computed(() => store.state.user)
    }
  },
  data() {
    return {
      active: 1,
      stars: [1, 2, 3, 4, 5],
      property: [],
      lat: 0,
      lng: 0,
      maps_src: "",
      images_count: 0,
      id: this.$route.params.id,
      status: 'Available',
      comments: [],
      comment: {
        property_id: this.$route.params.id,
        client: null,
        content: "",
        rating: 0
      },
      comment_content: "",
    };
  },
  methods: {
    async getPropertyImages() {
      //get images count
      await this.getImagesCount();
      let images = [];
      for (let i = 0; i < this.images_count; i++) {
        await axios.get('http://localhost:8080/uploads/properties/' + this.id + '/images/' + i, {responseType: 'arraybuffer'})
            .then(response => {
              console.log("Property Image: ", response.data)
              const imageBlob = new Blob([response.data], {type: 'image/jpeg'});
              const imageUrl = URL.createObjectURL(imageBlob);
              images.push({
                id: i,
                url: imageUrl
              })
            })
      }
      this.property.images = images;
    },
    async getImagesCount() {
      await axios.get('http://localhost:8080/uploads/properties/' + this.id + '/images/count')
          .then(response => {
            console.log("Property Image Count: ", response.data)
            this.images_count = response.data;
            console.log("Property Image Count: ", this.images_count)
          })
    },
    select(index) {
      this.selected = index;
      this.comment.rating = this.stars[index];
      console.log("rating: ", this.comment.rating)
    },
    checkAvailability() {
      axios.get(`http://localhost:8080/manage-properties/property/${this.id}/availability`)
          .then(response => {
            this.status = response.data
          })
          .catch(error => {
            console.error(error)
          })

    },
    async addComment() {
      this.comment.client = this.user;
      const comment = this.comment;
      console.log("comment: ", this.comment)
      await axios.post(`http://localhost:8080/manage-properties/${this.id}/add-comment`, comment)
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
    },
    async getComments() {
      await axios.get(`http://localhost:8080/manage-properties/${this.id}/comments`)
          .then((response) => {
            this.comments = response.data;
            console.log("Comments: ", this.comments);
          })
          .catch((error) => {
            console.log(error);
          });
    },
    async getProperty() {
      axios.get(`http://localhost:8080/manage-properties/properties/${this.id}`)
          .then((response) => {
            this.property = response.data;
            this.property.title = this.property.category + ' in ' + this.property.location.city
            this.lat = this.property.location.latitude;
            this.lng = this.property.location.longitude;

            //fill form src
            this.maps_src = `https://www.google.com/maps/embed/v1/place?q=${this.lng},${this.lng}&maptype=satellite&key=AIzaSyB-micHZxFKc5PDvE_4Uq4KqrrGy-Xz4H8`

            console.log("Properties: ", this.property);

            this.loadMap(this.lat, this.lng);
          })
          .catch((error) => {
            console.log(error);
          });
    },
    loadMap(lat, lng) {
      //put ifram in div of id embed-map
      document.getElementById('embed-map').innerHTML = `<iframe
              class="mb-3"
              width="700"
              height="300"
              frameborder="0"
              style="border:0"
              src="https://www.google.com/maps/embed/v1/place?q=${lat},${lng}&maptype=satellite&key=AIzaSyB-micHZxFKc5PDvE_4Uq4KqrrGy-Xz4H8"
              allowfullscreen
          ></iframe>`
    }
  },
  mounted() {
    this.checkAvailability();
    this.getProperty();
    this.getPropertyImages();
    this.getComments();
  },
}
</script>

<style scoped>
.disabled-btn {
  cursor: not-allowed;
}
</style>
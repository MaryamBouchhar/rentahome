<template>
  <div>
    <div class="flex flex-col lg:flex-row my-5">
      <div class="property-images px-4">
        <div class="carousel w-full" id="images">
          <div id="item1" class="carousel-item w-full">
            <img src="../assets/images/hero_img_1.jpg" class="w-full"/>
          </div>
          <div id="item2" class="carousel-item w-full">
            <img src="../assets/images/hero_img_2.jpg" class="w-full"/>
          </div>
          <div id="item3" class="carousel-item w-full">
            <img src="../assets/images/hero_img_3.jpg" class="w-full"/>
          </div>
          <div id="item4" class="carousel-item w-full">
            <img src="../assets/images/hero_img_4.jpg" class="w-full"/>
          </div>
        </div>
        <div class="flex justify-center w-full py-2 gap-2">
          <a href="#item1" class="btn btn-xs">1</a>
          <a href="#item2" class="btn btn-xs">2</a>
          <a href="#item3" class="btn btn-xs">3</a>
          <a href="#item4" class="btn btn-xs">4</a>
        </div>
        <!-- comments section -->
        <h1 class="text-2xl font-bold mb-3">Comments</h1>
        <div class="chat chat-start mb-3">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full">
              <img src="https://i.pravatar.cc/50"/>
            </div>
          </div>
          <div class="chat-bubble">It was said that you would, destroy the Sith, not join them.</div>
        </div>
        <div class="chat chat-start">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full">
              <img src="https://i.pravatar.cc/50"/>
            </div>
          </div>
          <div class="chat-bubble">It was you who would bring balance to the Force</div>
        </div>
        <div class="chat chat-start">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full">
              <img src="https://i.pravatar.cc/50"/>
            </div>
          </div>
          <div class="chat-bubble">Not leave it in Darkness</div>
        </div>

        <!-- add comment -->
        <div class="flex flex-col mt-3">
          <h1 class="text-2xl font-bold mb-3">Rate this property</h1>
          <textarea class="textarea h-24 textarea-secondary mb-3" placeholder="Your comment"></textarea>
          <div class="rating">
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400" checked/>
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
          </div>
          <button class="btn btn-wide mt-3">Add Comment</button>
        </div>
      </div>
      <div class="property-details w-full mx-4">
        <h1 class="text-3xl font-bold mb-3">{{ property.title }}</h1>
        <div class="flex flex-row items-center gap-2 mb-3">
          <div class="rating rating-sm">
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
          </div>
          <span class="rate-count">({{ property.total_rating }})</span>
        </div>
        <p class="mb-3">{{ property.description }}</p>
        <h1 class="font-bold text-xl text-green-400 mb-3">${{ property.price }} / month</h1>
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
              <td>{{ property.rooms_count }}</td>
            </tr>
            </tbody>
          </table>
        </div>
        <iframe
            class="mb-3"
            src="https://www.google.com/maps/embed/v1/view?key=AIzaSyCUwB_Qhwh8EswnSDcmb2Hqe2rDceyti14&center=40.712776,-74.005974&zoom=18"
            width="800" height="340" style="border:0;" allowfullscreen="" loading="lazy"
            referrerpolicy="no-referrer-when-downgrade"></iframe>
        <button class="btn btn-wide w-full">Book now</button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "PropertyDetails",
  data() {
    return {
      active: 1,
      property_id: this.$route.params.id,
      property: {
        id: 3,
        title: 'Condo in Montreal',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
        price: 1000,
        rating: 4,
        total_rating: 143,
        status: 'For Rent',
        category: 'Condo',
        rent_type: 'Monthly',
        rooms_count: 3,
        city: 'Montreal',
        image: 'https://picsum.photos/500/300?random=3',
      },
    };
  },
  mounted() {
    console.log(this.property_id)
  },
}
</script>

<style scoped>

</style>
<template>
  <div>
    <div class="flex flex-col lg:flex-row my-5">
      <div class="property-images w-full lg:w-full px-4">
        <div class="carousel w-full">
          <div id="item1" class="carousel-item w-full">
            <img src="../assets/hero_img.jpeg" class="w-full"/>
          </div>
          <div id="item2" class="carousel-item w-full">
            <img src="../assets/hero_img.jpeg" class="w-full"/>
          </div>
          <div id="item3" class="carousel-item w-full">
            <img src="../assets/hero_img.jpeg" class="w-full"/>
          </div>
          <div id="item4" class="carousel-item w-full">
            <img src="../assets/hero_img.jpeg" class="w-full"/>
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
              <img src="https://i.pravatar.cc/50" />
            </div>
          </div>
          <div class="chat-bubble">It was said that you would, destroy the Sith, not join them.</div>
        </div>
        <div class="chat chat-start">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full">
              <img src="https://i.pravatar.cc/50" />
            </div>
          </div>
          <div class="chat-bubble">It was you who would bring balance to the Force</div>
        </div>
        <div class="chat chat-start">
          <div class="chat-image avatar">
            <div class="w-10 rounded-full">
              <img src="https://i.pravatar.cc/50" />
            </div>
          </div>
          <div class="chat-bubble">Not leave it in Darkness</div>
        </div>

        <!-- add comment -->
        <div class="flex flex-col mt-3">
          <h1 class="text-2xl font-bold mb-3">Rate this property</h1>
          <textarea class="textarea h-24 mb-3" placeholder="Your comment"></textarea>
          <div class="rating">
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400" />
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400" checked />
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400" />
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400" />
            <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400" />
          </div>
          <button class="btn btn-wide mt-3">Add Comment</button>
        </div>
      </div>
      <div class="property-details w-full mx-4">
        <h1 class="text-3xl font-bold mb-3">Property title</h1>
        <div class="flex flex-row items-center gap-2 mb-3">
          <div class="rating rating-sm">
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
            <input class="mask mask-star-2 bg-orange-400"/>
          </div>
          <span class="rate-count">(143)</span>
        </div>
        <p class="mb-3">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium autem commodi corporis cum
          deleniti
          eius error eveniet excepturi facilis illo iusto magnam magni minima molestias nam natus, perspiciatis placeat
          vel.</p>
        <h1 class="font-bold text-xl text-green-400 mb-3">$1,300</h1>
        <div class="overflow-x-auto mb-3">
          <table class="table w-full">
            <tbody>
            <!-- row 1 -->
            <tr>
              <th class="font-bold text-lg">Category</th>
              <td>Cy Ganderton</td>
            </tr>
            <!-- row 2 -->
            <tr class="active">
              <th class="font-bold text-lg">Rent type</th>
              <td>Hart Hagerty</td>
            </tr>
            <!-- row 3 -->
            <tr>
              <th class="font-bold text-lg">Rooms count</th>
              <td>Brice Swyre</td>
            </tr>
            </tbody>
          </table>
        </div>
        <button class="btn btn-wide w-full">Book now</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PropertyDetails"
}
</script>

<style scoped>

</style>
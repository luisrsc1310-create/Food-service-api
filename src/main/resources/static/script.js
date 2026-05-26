const API_URL = "http://localhost:8080/foods";
const API_URLCATEGORY = "http://localhost:8080/categories"

const form = document.getElementById("food-form")
const formToUpdate = document.getElementById("foodUpdate-form")
const deleteButton = document.getElementById("deleteButton")
let selectedFoodId = null;


function addFood() {
    event.preventDefault();
    const name = document.getElementById("name").value;
    const price = document.getElementById("price").value;
    const type = document.getElementById("type").value;
    const categoryId = document.getElementById("categorySelect").value;

    const food = {
        name: name, price: parseFloat(price), type: type, categoryId: categoryId
    };

    fetch(API_URL, {
        method: "POST", headers: {
            "Content-Type": "application/json"
        }, body: JSON.stringify(food)
    })
        .then(res => {
            if (!res.ok) {
                throw new Error("Error saving food")
            }
            return res.json();
        })

        .then(data => {
            form.reset();
            loadFoods();
        })

        .catch(err => {
            document.getElementById("message").textContent = err.messages;
        });


}

form.addEventListener("submit", addFood);
formToUpdate.addEventListener("submit", updateFoods);
deleteButton.addEventListener("click", function () {
    deleteFood();
});

function loadCategories(selectId) {

    const category = {
        name: name
    }

    fetch(API_URLCATEGORY)
        .then(res => res.json())
        .then(data => {

            const select = document.querySelectorAll(`.categorySelect`)

            data.content.forEach(category => {
                const option = new Option(`${category.name}`, category.id);
                const select = document.getElementById(selectId);

                select.add(option);


            })
        })


}

function loadFoods() {


    fetch(API_URL)
        .then(res => res.json())
        .then(data => {

            const list = document.getElementById("food-list");
            list.innerHTML = "";

            data.content.forEach((food, index) => {


                const tr = document.createElement("tr");


                tr.innerHTML = `
    
                        <td>${index + 1}</td>
                        <td>${food.name}</td>
                        <td>${food.price}</td>
                        <td>${food.categoryName}</td>
                        <td>
                         <div class="">
                             <button type="button" class="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#updateModal" onclick="fillForm(${food.id}, '${food.name}', ${food.price}, )">Update</button>
                                <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" onclick="openDeleteModal(${food.id})" data-bs-target="#deleteModal">Delete</button>
                         </div>
                        </td>
                `;

                list.appendChild(tr);
            });
        });
}

function fillForm(id, name, price) {
    document.getElementById("idToUpdate").value = id;
    document.getElementById("nameToUpdate").value = name;
    document.getElementById("priceToUpdate").value = price;
}

function updateFoods() {

    const id = document.getElementById("idToUpdate").value;


    const nameUpdate = document.getElementById("nameToUpdate").value;
    const priceUpdate = document.getElementById("priceToUpdate").value;
    const typeUpdate = document.getElementById("typeSelectUpdate").value;
    const categoryId = document.getElementById("categorySelectUpdate").value;


    if (!nameUpdate || !priceUpdate) {
        document.getElementById("message").textContent = "Fill all required fields!";
        return;
    }


    const food = {

        name: nameUpdate,
        price: parseFloat(priceUpdate),
        type: typeUpdate,
        categoryId: categoryId
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT", headers: {
            "Content-Type": "application/json"
        }, body: JSON.stringify(food)
    })

        .then(res => {
            if (!res.ok) {
                throw new Error("Error updating food")
            }

            return res.json();
        })

        .then(dataUpdate => {
            form.reset();
            loadFoods();
        })

        .catch(err => {
            document.getElementById("message").textContent = err.messages;
        })


}

function deleteFood() {

    if (selectedFoodId !== null) {

        fetch(`${API_URL}/${selectedFoodId}`, {
            method: "DELETE"
        })
            .then(() => {
                loadFoods();
                const modalElement = document.getElementById("deleteModal");
                const modal = bootstrap.Modal.getInstance(modalElement);
                modal.hide();
                selectedFoodId = null; // reset
            });
    }
}

function openDeleteModal(id) {
    selectedFoodId = id;
}

// Load on start
loadFoods();
loadCategories("categorySelect");
loadCategories("categorySelectUpdate")
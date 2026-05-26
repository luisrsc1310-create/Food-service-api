const API_URL = "http://localhost:8080/orders";
    const API_URLFOODS = "http://localhost:8080/foods";

const createButton = document.getElementById("createOrder");
createButton.addEventListener("click", fillFoodsCard);

function loadOrders() {

    fetch(API_URL)
        .then(res => res.json())
        .then(data => {


            const ordersList = document.getElementById("order-list");

            ordersList.innerHTML = "";

            data.content.forEach((order, index) => {


                const tr = document.createElement("tr");


                tr.innerHTML = `
    
                        <td>${index + 1}</td>
                        <td>${order.status}</td>
                        <td>
                                <button type="button" class="btn btn-primary">Show Items</button>
                        </td>
                        <td>${order.total}</td>
                         <div class="">
                            <button type="button" class="btn btn-warning" data-bs-toggle="modal" onclick="fillForm('${order.id}', '${order.status}')" data-bs-target="#updateModalOrder">Update</button>
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" onclick="openDeleteModal(${order.id})" data-bs-target="#deleteModalOrder">Delete</button>
                         </div>
                        </td>
                `;

                ordersList.appendChild(tr);


            })

        })

}

function addOrders() {

    const status= document.getElementById("status").value;
    const total = document.getElementById("total").value;

    const order = {
        status: status, total: parseFloat(total)
    };


    fetch(API_URL,  {

        method: "POST", headers: {
            "Content-Type": "application/json"
        }, body: json.stringify(order)
    })

        .then(res =>  {
            if(!res.ok) {
                throw new Error("Error saving food")
        }
            return res.json();
    })

        .then(data => {
            form.reset();
            loadOrders();
        })

        .catch(err => {
            document.getElementById("message").textContent = err.messages;
        })


}

function fillFoodsCard() {

    fetch(API_URLFOODS)
        .then(res => res.json())
        .then(data => {

            const div = document.getElementById("cardFoodDiv");

            div.innerHTML = "";

            data.content.forEach((food) => {

                const col = document.createElement("div");

                col.innerHTML = `
                    <div class="col">
                        <div class="card">
                            <img src="..." class="card-img-top" alt="...">

                            <div class="card-body">
                                <h5 class="card-title">${food.name}</h5>
                                <p>
                                    <strong>Food Price:</strong> ${food.price}
                                </p>
                                <p class="card-text">
                                    ${food.categoryName}
                                </p>
                            </div>
                        </div>
                    </div>
                `;

                div.appendChild(col);
            });
        });
}
function fillForm(id, status) {

    document.getElementById("idToUpdateOrder").value = id;

    document.getElementById("statusToUpdate").value = status;

}

loadOrders();



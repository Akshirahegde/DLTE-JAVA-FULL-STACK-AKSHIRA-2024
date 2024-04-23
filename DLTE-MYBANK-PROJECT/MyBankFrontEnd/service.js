
function displayDepositCards() {
    // Retrieve deposit details from local storage
    var deposits = JSON.parse(localStorage.getItem("deposits"));

    // Display deposit cards
    var depositDetailsContainer = document.getElementById("depositDetails");
    depositDetailsContainer.innerHTML = ''; 
    deposits.forEach(function(deposit) {
        var cardHtml = `
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h2 class="card-title text-center">${deposit.name}</h2>
                        <hr>
                        <div class="card-text text-center">
                            <p>ROI: ${deposit.roi}%</p>
                        </div>
                        <div id="${deposit.id}Details" class="collapse">
                            <hr>
                            <div class="card-text">
                                <p>Type: ${deposit.type}</p>
                                <p>${deposit.description}</p>
                            </div>
                        </div>
                        <div class="text-center">
                            <button class="btn btn-outline-primary" data-bs-toggle="collapse" data-bs-target="#${deposit.id}Details" aria-expanded="false" aria-controls="${deposit.id}Details" onclick="toggleButton(this)">Show Details</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
        depositDetailsContainer.innerHTML += cardHtml;
    });
}

var deposits = [
    { id: 1, name: "Fixed Savings", roi: 7.9, type: "Term Deposit", description: "A fixed-term savings account" },
    { id: 2, name: "Flexi Saver", roi: 6.8, type: "Savings Account", description: "A flexible savings account" },
    { id: 3, name: "Term Deposits", roi: 7.2, type: "Fixed Deposit", description: "Flexible Terms, Fixed Returns,Security " },
    { id: 4, name: "Savings deposits", roi: 8.01, type: "Fixed Deposit", description: "Traditional brick-and-mortar banks typically offer lower interest rates" },
    { id: 5, name: "Reccurent Deposits", roi: 6.9, type: "Reccuring Account", description: "Minimum Investment Requirements" },
    { id: 6, name: "Wealth Grower", roi: 7.4, type: "Savings Account", description: "  Flexible Terms, Fixed Returns,Security " }
];

localStorage.setItem("deposits", JSON.stringify(deposits));

function toggleButton(button) {
    if (button.innerHTML === 'Show Less') {
        button.innerHTML = 'Show Details';
    } else {
        button.innerHTML = 'Show Less';
    }
}
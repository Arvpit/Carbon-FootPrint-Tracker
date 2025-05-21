function calculateFootprint() {
    const milesDriven = parseFloat(document.getElementById('milesDriven').value);
    const electricityUsed = parseFloat(document.getElementById('electricityUsed').value);
    const mealsConsumed = parseFloat(document.getElementById('mealsConsumed').value);

    // Validate inputs
    if (isNaN(milesDriven) || isNaN(electricityUsed) || isNaN(mealsConsumed)) {
        alert("Please enter valid numbers for all fields.");
        return;
    }

    // Send data to the servlet using AJAX
    fetch('/EcoTrack/calculateFootprint', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            milesDriven: milesDriven,
            electricityUsed: electricityUsed,
            mealsConsumed: mealsConsumed
        }).toString()
    })
    .then(response => response.text())
    .then(result => {
        // Display the result in the results div
        document.getElementById('results').innerHTML = result;
    })
    .catch(error => console.error('Error:', error));
}
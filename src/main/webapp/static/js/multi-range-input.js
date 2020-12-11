let containers = document.querySelectorAll('.multi-range-container');

for (let container of containers) {

    (function () {
        let lowerSlider = container.querySelector('.lower-slider');
        let upperSlider = container.querySelector('.upper-slider');
        let lowerField = container.querySelector('.lower-value');
        let upperField = container.querySelector('.upper-value');
        let lowerVal;
        let upperVal;

        upperSlider.oninput = function () {
            lowerVal = parseInt(lowerSlider.value);
            upperVal = parseInt(upperSlider.value);
            upperField.value = upperVal;

            if (upperVal < lowerVal) {
                lowerSlider.value = upperVal;
                lowerField.value = upperVal;

                if (lowerVal === lowerSlider.min) {
                    upperSlider.value = lowerVal;
                    upperField.value = lowerVal;
                }
            }
        };

        lowerSlider.oninput = function () {
            lowerVal = parseInt(lowerSlider.value);
            upperVal = parseInt(upperSlider.value);
            lowerField.value = lowerVal;

            if (lowerVal > upperVal) {
                upperSlider.value = lowerVal;
                upperField.value = lowerVal;

                if (upperVal === upperSlider.max) {
                    lowerSlider.value = upperVal;
                    lowerSlider.value = upperVal;
                }
            }
        };

        upperField.oninput = function () {
            lowerVal = parseInt(lowerField.value);
            upperVal = parseInt(upperField.value);
            upperSlider.value = upperVal;

            if (upperVal < lowerVal) {
                lowerSlider.value = upperVal;
                lowerField.value = upperVal;

                if (lowerVal === lowerSlider.min) {
                    upperSlider.value = lowerVal;
                    upperField.value = lowerVal;
                }
            }
        };

        lowerField.oninput = function () {
            lowerVal = parseInt(lowerField.value);
            upperVal = parseInt(upperField.value);
            lowerSlider.value = lowerVal;

            if (lowerVal > upperVal) {
                upperSlider.value = lowerVal;
                upperField.value = lowerVal;

                if (upperVal === upperSlider.max) {
                    lowerSlider.value = upperVal;
                    lowerField.value = upperVal;
                }
            }
        };
    })();

}
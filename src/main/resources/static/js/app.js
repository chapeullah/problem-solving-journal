document.addEventListener("DOMContentLoaded", () => {
    const topicsList = document.querySelector("[data-topics-list]");
    const addTopicButton = document.querySelector("[data-add-topic]");
    const topicTemplate = document.querySelector("#topic-row-template");

    const topicRows = () =>
        topicsList ? [...topicsList.querySelectorAll(".topic-row")] : [];

    const refreshTopicInputs = () => {
        topicRows().forEach((row, index) => {
            const input = row.querySelector("input[name='topics']");
            const label = row.querySelector("label");

            if (!input) {
                return;
            }

            input.id = `topic-${index}`;
            if (label) {
                label.htmlFor = input.id;
            }
        });
    };

    addTopicButton?.addEventListener("click", () => {
        if (!topicsList || !topicTemplate) {
            return;
        }

        const row = topicTemplate.content.firstElementChild.cloneNode(true);
        topicsList.append(row);
        refreshTopicInputs();
        row.querySelector("input")?.focus();
    });

    topicsList?.addEventListener("click", event => {
        const removeButton = event.target.closest("[data-remove-topic]");
        if (!removeButton) {
            return;
        }

        const rows = topicRows();
        const row = removeButton.closest(".topic-row");

        if (rows.length === 1) {
            const input = row?.querySelector("input");
            if (input) {
                input.value = "";
                input.focus();
            }
            return;
        }

        row?.remove();
        refreshTopicInputs();
    });

    document.querySelector("[data-task-form]")?.addEventListener("submit", () => {
        const seenTopics = new Set();

        topicRows().forEach(row => {
            const input = row.querySelector("input[name='topics']");
            if (!input) {
                return;
            }

            input.value = input.value.trim();
            const normalized = input.value.toLocaleLowerCase();

            if (normalized && seenTopics.has(normalized) && topicRows().length > 1) {
                row.remove();
                return;
            }

            if (normalized) {
                seenTopics.add(normalized);
            }
        });
    });

    document.querySelectorAll("form[data-confirm]").forEach(form => {
        form.addEventListener("submit", event => {
            const message = form.dataset.confirm;
            if (message && !window.confirm(message)) {
                event.preventDefault();
            }
        });
    });
});

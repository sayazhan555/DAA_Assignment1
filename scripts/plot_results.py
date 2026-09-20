import csv
import math
import matplotlib.pyplot as plt
from collections import defaultdict

INPUT_FILE = "results.csv"

data = defaultdict(lambda: {
    "n": [],
    "time": [],
    "comparisons": [],
    "depth": []
})

with open(INPUT_FILE, "r", newline="") as file:
    reader = csv.DictReader(file)

    for row in reader:
        algorithm = row["algorithm"]
        input_type = row["input"]

        key = (algorithm, input_type)

        data[key]["n"].append(int(row["n"]))
        data[key]["time"].append(float(row["time_ms"]))
        data[key]["comparisons"].append(int(row["comparisons"]))
        data[key]["depth"].append(int(row["max_depth"]))


def sort_data(values):
    pairs = sorted(zip(values["n"], range(len(values["n"]))))
    indices = [index for _, index in pairs]

    return (
        [values["n"][i] for i in indices],
        [values["time"][i] for i in indices],
        [values["comparisons"][i] for i in indices],
        [values["depth"][i] for i in indices]
    )


# 1. Time vs n
plt.figure()

for (algorithm, input_type), values in data.items():
    n, time, _, _ = sort_data(values)

    plt.plot(
        n,
        time,
        marker="o",
        label=f"{algorithm} - {input_type}"
    )

plt.xlabel("n")
plt.ylabel("Time (ms)")
plt.title("Time vs n")
plt.xscale("log")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig("time_vs_n.png", dpi=200)
plt.close()


# 2. Max recursion depth vs n
plt.figure()

for (algorithm, input_type), values in data.items():
    n, _, _, depth = sort_data(values)

    plt.plot(
        n,
        depth,
        marker="o",
        label=f"{algorithm} - {input_type}"
    )

plt.xlabel("n")
plt.ylabel("Max recursion depth")
plt.title("Max Recursion Depth vs n")
plt.xscale("log")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig("depth_vs_n.png", dpi=200)
plt.close()


# 3. Ratio vs n
plt.figure()

for (algorithm, input_type), values in data.items():
    n, _, comparisons, _ = sort_data(values)

    ratio = []

    for size, comp in zip(n, comparisons):
        if algorithm == "QuickSelect":
            ratio.append(comp / size)
        else:
            ratio.append(comp / (size * math.log2(size)))

    plt.plot(
        n,
        ratio,
        marker="o",
        label=f"{algorithm} - {input_type}"
    )

plt.xlabel("n")
plt.ylabel("Comparison ratio")
plt.title("Normalized Comparison Ratio vs n")
plt.xscale("log")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig("ratio_vs_n.png", dpi=200)
plt.close()


print("Graphs created successfully.")
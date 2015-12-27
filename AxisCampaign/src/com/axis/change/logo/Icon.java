package com.axis.change.logo;

public class Icon {
	private Duration duration;

	private File file;

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "ClassPojo [duration = " + duration + ", file = " + file + "]";
	}
}

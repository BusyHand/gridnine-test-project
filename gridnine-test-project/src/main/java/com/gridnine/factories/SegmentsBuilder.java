package com.gridnine.factories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gridnine.models.Segment;
/**
 * Factory class to get sample list of segment.
 */
@Component
public class SegmentsBuilder {

	public List<Segment> createSegments(final LocalDateTime[] dates) {
		List<Segment> segments = new ArrayList<>(dates.length / 2);
		for (int i = 0; i < (dates.length - 1); i += 2) {
			segments.add(new Segment(dates[i], dates[i + 1]));
		}
		return segments;
	}
}
